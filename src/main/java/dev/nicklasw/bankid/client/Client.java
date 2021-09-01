package dev.nicklasw.bankid.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nicklasw.bankid.client.request.Request;
import dev.nicklasw.bankid.client.response.JsonBodyHandler;
import dev.nicklasw.bankid.client.response.Response;
import dev.nicklasw.bankid.client.response.ResponseWrapper;
import dev.nicklasw.bankid.client.ssl.SSLContexts;
import dev.nicklasw.bankid.configuration.Configuration;
import dev.nicklasw.bankid.configuration.Pkcs12;
import dev.nicklasw.bankid.exceptions.BankIdApiErrorException;
import dev.nicklasw.bankid.exceptions.BankIdException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Client {

    private final HttpClient httpClient;
    private final Configuration configuration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Returns a {@link Client} with the given configuration.
     *
     * @param configuration the configuration
     * @return an {@link Client}
     * @throws BankIdException in case of error
     */
    public static Client of(final Configuration configuration) {
        try {
            return new Client(HttpClient.newBuilder()
                .sslContext(sslContext(configuration)).build(), configuration);
        } catch (final Exception e) {
            throw new BankIdException(e);
        }
    }

    /**
     * Sends the given request, blocking to retrieve the response.
     *
     * @param request the request to be sent.
     * @param <R>     the response type
     * @return the response
     * @throws BankIdException in case of an error
     */
    public <R extends Response> R sendRequest(@NonNull final Request request, @NonNull final Class<R> responseClass) {
        final HttpResponse<ResponseWrapper<R>> response;

        try {
            response = httpClient
                .send(httpRequest(request), new JsonBodyHandler<>(responseClass, objectMapper));
        } catch (final IOException | InterruptedException e) {
            throw new BankIdException(e);
        }

        return unwrap(response);
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
        return httpClient
            .sendAsync(httpRequest(request), new JsonBodyHandler<>(responseClass, objectMapper))
            .thenApplyAsync(Client::unwrap);
    }

    private HttpRequest httpRequest(final Request request) {
        return HttpRequest.newBuilder()
            .uri(URI.create(configuration.getBaseURL() + request.getUri()))
            .header("Content-Type", "application/json")
            .timeout(Duration.ofMinutes(1))
            .POST(HttpRequest.BodyPublishers.ofString(body(request)))
            .build();
    }

    @SneakyThrows
    private String body(final Request request) {
        return objectMapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(request);
    }

    private static SSLContext sslContext(final Configuration configuration) {
        final Pkcs12 pkcs12 = configuration.getPkcs12();

        return SSLContexts.builder()
            .loadKeyManager(pkcs12.getPath(), pkcs12.getPassword())
            .loadTrustManager(configuration.getCertificate())
            .build();
    }

    private static <R extends Response> R unwrap(final HttpResponse<ResponseWrapper<R>> response) {
        final R actualResponse = response.body().unwrap();

        if (actualResponse.hasErrorCode()) {
            throw BankIdApiErrorException.of(actualResponse);
        }

        return actualResponse;
    }

}
