package dev.nicklasw.bankid.internal.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nicklasw.bankid.client.request.Request;
import dev.nicklasw.bankid.client.response.ErrorResponse;
import dev.nicklasw.bankid.client.response.Response;
import dev.nicklasw.bankid.configuration.Configuration;
import dev.nicklasw.bankid.configuration.Pkcs12;
import dev.nicklasw.bankid.exceptions.BankIdApiErrorException;
import dev.nicklasw.bankid.exceptions.BankIdApiUnexpectedResponseException;
import dev.nicklasw.bankid.exceptions.BankIdException;
import dev.nicklasw.bankid.internal.annotations.Internal;
import dev.nicklasw.bankid.internal.ssl.SslUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Internal
public class Client {

    private final HttpClient httpClient;
    private final Configuration configuration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Client(final HttpClient httpClient, final Configuration configuration) {
        this.httpClient = httpClient;
        this.configuration = configuration;
    }

    /**
     * Returns a {@link Client} with the given configuration.
     *
     * @param configuration the configuration
     * @return an {@link Client}
     * @throws BankIdException in case of an error
     */
    public static Client of(final Configuration configuration) {
        try {
            return new Client(HttpClient.newBuilder()
                .sslContext(sslContext(configuration)).build(), configuration);
        } catch (final Throwable e) {
            throw new BankIdException(e);
        }
    }

    /**
     * Sends the given request synchronously using the HTTP client and returns the response of the specified class.
     *
     * @param request       the request to be sent
     * @param responseClazz the response class type
     * @param <R>           the response type
     * @return the response of the specified class
     * @throws BankIdException                      in case of an unexpected error
     * @throws BankIdApiErrorException              in case of an api error
     * @throws BankIdApiUnexpectedResponseException in case of an unexpected api error
     */
    public <R extends Response> R sendRequest(final Request request, final Class<R> responseClazz) {
        final HttpResponse<R> response;

        try {
            response = httpClient
                .send(httpRequest(request), new JsonBodyHandler<>(responseClazz, ErrorResponse.class, objectMapper));
        } catch (final IOException | InterruptedException e) {
            if (e.getCause() instanceof final BankIdException bankIdException) {
                throw bankIdException;
            }

            throw new BankIdException(e);
        }

        return response.body();
    }

    /**
     * Sends the given request asynchronously using this client with the given response class.
     * <p>
     * Propagates {@link BankIdException} in case of error.
     *
     * @param request       the request to be sent.
     * @param responseClass the response class typel
     * @param <R>           the response type
     * @return a {@code CompletableFuture<Response<T>>}
     */
    public <R extends Response> CompletableFuture<R> sendRequestAsync(final Request request, final Class<R> responseClass) {
        final HttpRequest httpRequest;
        try {
            httpRequest = httpRequest(request);
        } catch (final JsonProcessingException e) {
            return CompletableFuture.failedFuture(e);
        }

        return httpClient
            .sendAsync(httpRequest, new JsonBodyHandler<>(responseClass, ErrorResponse.class, objectMapper))
            .thenApplyAsync(HttpResponse::body);
    }

    private HttpRequest httpRequest(final Request request) throws JsonProcessingException {
        return HttpRequest.newBuilder()
            .uri(URI.create(configuration.baseURL() + request.getUri()))
            .header("Content-Type", "application/json")
            .timeout(Duration.ofMinutes(1))
            .POST(HttpRequest.BodyPublishers.ofString(body(request)))
            .build();
    }

    private String body(final Request request) throws JsonProcessingException {
        return objectMapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(request);
    }

    private static SSLContext sslContext(final Configuration configuration) throws GeneralSecurityException, IOException {
        final Pkcs12 pkcs12 = configuration.pkcs12();

        return SslUtils.tryCreateSSLContext(
            SslUtils.tryCreateKeyManager(pkcs12.inputStream(), pkcs12.password()),
            SslUtils.tryCreateTrustManager(configuration.certificate())
        );
    }

}
