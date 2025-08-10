package dev.eidentification.bankid.internal.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.eidentification.bankid.client.response.ErrorResponse;
import dev.eidentification.bankid.client.response.Response;
import dev.eidentification.bankid.exceptions.BankIdApiErrorException;
import dev.eidentification.bankid.exceptions.BankIdApiUnexpectedResponseException;
import dev.eidentification.bankid.internal.annotations.Internal;

import java.io.InputStream;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Internal
public class JsonBodyHandler<R extends Response, E extends ErrorResponse> implements HttpResponse.BodyHandler<R> {
    private static final int HTTP_STATUS_OK = 200;

    private final Class<R> responseClazz;
    private final Class<E> errorResponseClazz;
    private final ObjectMapper objectMapper;

    JsonBodyHandler(final Class<R> responseClazz, final Class<E> errorResponseClazz, final ObjectMapper objectMapper) {
        this.responseClazz = responseClazz;
        this.errorResponseClazz = errorResponseClazz;
        this.objectMapper = objectMapper;
    }

    /**
     * Returns a {@link HttpResponse.BodySubscriber BodySubscriber}.
     *
     * @param responseInfo the response info
     * @return the body subscriber
     * @throws BankIdApiErrorException              in case of an api error
     * @throws BankIdApiUnexpectedResponseException in case of an unexpected api error
     */
    @Override
    public HttpResponse.BodySubscriber<R> apply(final HttpResponse.ResponseInfo responseInfo) {
        final HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        if (responseInfo.statusCode() == HTTP_STATUS_OK) {
            return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> inputStreamTo(responseClazz, responseInfo, inputStream));
        }

        final Optional<String> optionalContentType = responseInfo.headers()
            .firstValue("Content-Type")
            .map(String::toLowerCase);

        if (optionalContentType.isEmpty() || !optionalContentType.get().contains("application/json")) {
            return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> {
                throw BankIdApiUnexpectedResponseException.of(responseInfo, inputStreamToString(responseInfo, inputStream));
            });
        }

        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> {
            throw BankIdApiErrorException.of(inputStreamTo(errorResponseClazz, responseInfo, inputStream));
        });
    }

    private <T> T inputStreamTo(final Class<T> targetType, final HttpResponse.ResponseInfo responseInfo, final InputStream inputStream) {
        String body = "";

        try (final InputStream stream = inputStream) {
            body = new String(stream.readAllBytes(), StandardCharsets.UTF_8);

            return objectMapper.readValue(body, targetType);
        } catch (final Exception e) {
            throw BankIdApiUnexpectedResponseException.of(responseInfo, body, e);
        }
    }

    private static String inputStreamToString(final HttpResponse.ResponseInfo responseInfo, final InputStream inputStream) {
        String body = "";

        try (final InputStream stream = inputStream) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (final Exception e) {
            throw BankIdApiUnexpectedResponseException.of(responseInfo, body, e);
        }
    }
}
