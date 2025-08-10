package dev.eidentification.bankid.internal.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.eidentification.bankid.UnitTest;
import dev.eidentification.bankid.client.response.ErrorResponse;
import dev.eidentification.bankid.client.response.SignResponse;
import dev.eidentification.bankid.exceptions.BankIdApiErrorException;
import dev.eidentification.bankid.exceptions.BankIdApiUnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

class JsonBodyHandlerUnitTest extends UnitTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void givenStatus200AndValidJson_whenApply_thenParsesResponse() {
        final JsonBodyHandler<SignResponse, ErrorResponse> handler =
            new JsonBodyHandler<>(SignResponse.class, ErrorResponse.class, objectMapper);

        final HttpResponse.ResponseInfo info = responseInfo(200, "application/json; charset=utf-8");

        final String json = """
            {
              "orderRef":"131daac9-16c6-4618-beb0-365768f37288",
              "autoStartToken":"7c40b5c9-fa74-49cf-b98c-bfe651f9a7c6"
            }""";

        final HttpResponse.BodySubscriber<SignResponse> subscriber = handler.apply(info);
        final SignResponse result = complete(feed(subscriber, json));

        Assertions.assertNotNull(result);
        Assertions.assertEquals("131daac9-16c6-4618-beb0-365768f37288", result.getOrderRef());
        Assertions.assertEquals("7c40b5c9-fa74-49cf-b98c-bfe651f9a7c6", result.getAutoStartToken());
    }

    @Test
    void givenStatus200AndInvalidJson_whenApply_thenThrowsUnexpectedResponse() {
        final JsonBodyHandler<SignResponse, ErrorResponse> handler =
            new JsonBodyHandler<>(SignResponse.class, ErrorResponse.class, objectMapper);
        final HttpResponse.ResponseInfo info = responseInfo(200, "application/json");
        final String invalidJson = "not-a-json";

        final HttpResponse.BodySubscriber<SignResponse> subscriber = handler.apply(info);

        final BankIdApiUnexpectedResponseException ex = Assertions.assertThrows(
            BankIdApiUnexpectedResponseException.class,
            () -> complete(feed(subscriber, invalidJson))
        );
        Assertions.assertTrue(ex.getMessage() != null && !ex.getMessage().isBlank());
    }

    @Test
    void givenNon200AndMissingContentType_whenApply_thenThrowsUnexpectedResponse() {
        final JsonBodyHandler<SignResponse, ErrorResponse> handler =
            new JsonBodyHandler<>(SignResponse.class, ErrorResponse.class, objectMapper);
        final HttpResponse.ResponseInfo info = responseInfo(500, null);
        final String body = "<html>Server error</html>";

        final HttpResponse.BodySubscriber<SignResponse> subscriber = handler.apply(info);

        Assertions.assertThrows(BankIdApiUnexpectedResponseException.class, () -> complete(feed(subscriber, body)));
    }

    @Test
    void givenNon200AndNonJsonContentType_whenApply_thenThrowsUnexpectedResponse() {
        final JsonBodyHandler<SignResponse, ErrorResponse> handler =
            new JsonBodyHandler<>(SignResponse.class, ErrorResponse.class, objectMapper);
        final HttpResponse.ResponseInfo info = responseInfo(404, "text/plain");
        final String body = "Not found";

        final HttpResponse.BodySubscriber<SignResponse> subscriber = handler.apply(info);

        Assertions.assertThrows(BankIdApiUnexpectedResponseException.class, () -> complete(feed(subscriber, body)));
    }

    @Test
    void givenNon200AndJsonContentTypeWithValidJson_whenApply_thenThrowsApiError() {
        final JsonBodyHandler<SignResponse, ErrorResponse> handler =
            new JsonBodyHandler<>(SignResponse.class, ErrorResponse.class, objectMapper);
        final HttpResponse.ResponseInfo info = responseInfo(400, "application/json");
        final String errorJson = "{\"errorCode\":\"BAD_REQUEST\",\"details\":\"Invalid input\"}";

        final HttpResponse.BodySubscriber<SignResponse> subscriber = handler.apply(info);

        Assertions.assertThrows(BankIdApiErrorException.class, () -> complete(feed(subscriber, errorJson)));
    }

    @Test
    void givenNon200AndJsonContentTypeWithInvalidJson_whenApply_thenThrowsUnexpectedResponse() {
        final JsonBodyHandler<SignResponse, ErrorResponse> handler =
            new JsonBodyHandler<>(SignResponse.class, ErrorResponse.class, objectMapper);
        final HttpResponse.ResponseInfo info = responseInfo(500, "Application/JSON; charset=UTF-8"); // case-insensitive
        final String invalidJson = "{ this is broken }";

        final HttpResponse.BodySubscriber<SignResponse> subscriber = handler.apply(info);

        Assertions.assertThrows(BankIdApiUnexpectedResponseException.class, () -> complete(feed(subscriber, invalidJson)));
    }

    private static HttpResponse.ResponseInfo responseInfo(final int status, final String contentType) {
        final Map<String, List<String>> headerMap =
            contentType == null
                ? Map.of()
                : Map.of("Content-Type", List.of(contentType));

        final HttpHeaders headers = HttpHeaders.of(headerMap, (k, v) -> true);

        return new HttpResponse.ResponseInfo() {
            @Override
            public int statusCode() {
                return status;
            }

            @Override
            public HttpHeaders headers() {
                return headers;
            }

            @Override
            public HttpClient.Version version() {
                return HttpClient.Version.HTTP_1_1;
            }
        };
    }

    private static <T> T complete(final CompletableFuture<T> future) {
        try {
            return future.get(2, TimeUnit.SECONDS);
        } catch (final TimeoutException e) {
            fail("Timed out waiting for body subscriber to complete");
            return null; // unreachable
        } catch (final ExecutionException e) {
            if (e.getCause() instanceof RuntimeException re) {
                throw re;
            }
            throw new CompletionException(e.getCause());
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private static <T> CompletableFuture<T> feed(final HttpResponse.BodySubscriber<T> subscriber, final String body) {
        subscriber.onSubscribe(new NoopSubscription());
        subscriber.onNext(List.of(ByteBuffer.wrap(body.getBytes(StandardCharsets.UTF_8))));
        subscriber.onComplete();
        return subscriber.getBody().toCompletableFuture();
    }

    private static class NoopSubscription implements java.util.concurrent.Flow.Subscription {
        @Override
        public void request(final long n) {
            // no-op
        }

        @Override
        public void cancel() {
            // no-op
        }
    }
}
