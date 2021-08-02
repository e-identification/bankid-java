package dev.nicklasw.bankid.client.response;

import java.io.InputStream;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nicklasw.bankid.exceptions.BankIdApiUnexpectedResponseException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonBodyHandler<R extends Response> implements HttpResponse.BodyHandler<ResponseWrapper<R>> {
    @SuppressFBWarnings
    private final Class<R> responseClass;
    @SuppressFBWarnings
    private final ObjectMapper objectMapper;

    @Override
    public HttpResponse.BodySubscriber<ResponseWrapper<R>> apply(final HttpResponse.ResponseInfo responseInfo) {
        return asJSON(responseClass, responseInfo, objectMapper);
    }

    public static <W> HttpResponse.BodySubscriber<ResponseWrapper<W>> asJSON(final Class<W> targetType, final HttpResponse.ResponseInfo responseInfo,
                                                                             final ObjectMapper objectMapper) {
        final HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        return HttpResponse.BodySubscribers.mapping(
            upstream,
            inputStream -> toResponseWrapperOfType(inputStream, targetType, responseInfo, objectMapper));
    }

    public static <W> ResponseWrapper<W> toResponseWrapperOfType(final InputStream inputStream, final Class<W> targetType,
                                                                 final HttpResponse.ResponseInfo responseInfo, final ObjectMapper objectMapper) {
        return () -> {
            String body = "";

            try (final InputStream stream = inputStream) {
                body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                return objectMapper.readValue(body, targetType);
            } catch (final Exception e) {
                throw BankIdApiUnexpectedResponseException.of(responseInfo, body, e);
            }
        };
    }

}
