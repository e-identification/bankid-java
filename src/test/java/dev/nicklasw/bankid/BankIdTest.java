package dev.nicklasw.bankid;

import static dev.nicklasw.bankid.configuration.Configuration.URL_TEST;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import dev.nicklasw.bankid.client.model.Requirement;
import dev.nicklasw.bankid.client.model.UserVisibleData;
import dev.nicklasw.bankid.client.request.AuthenticationRequest;
import dev.nicklasw.bankid.client.request.CancelRequest;
import dev.nicklasw.bankid.client.request.CollectRequest;
import dev.nicklasw.bankid.client.request.SignRequest;
import dev.nicklasw.bankid.client.response.AuthenticateResponse;
import dev.nicklasw.bankid.client.response.CancelResponse;
import dev.nicklasw.bankid.client.response.CollectResponse;
import dev.nicklasw.bankid.client.response.OrderResponse;
import dev.nicklasw.bankid.client.response.SignResponse;
import dev.nicklasw.bankid.client.utils.ResourceUtils;
import dev.nicklasw.bankid.configuration.Configuration;
import dev.nicklasw.bankid.configuration.Pkcs12;
import dev.nicklasw.bankid.utils.RandomUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankIdTest {

    private BankId bankId;

    @Test
    void authenticate() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(
            AuthenticationRequest.builder()
                .personalNumber(personalNumber())
                .endUserIp("192.168.1.1")
                .build());

        assertOrder(authenticateResponse);
    }

    @Test
    @SneakyThrows
    void authenticateAsync() {
        given();

        final CompletableFuture<AuthenticateResponse> authenticateResponseCompletableFuture = bankId.authenticateAsync(
            AuthenticationRequest.builder()
                .personalNumber(personalNumber())
                .endUserIp("192.168.1.1")
                .requirement(Requirement.builder().build())
                .build());

        final AuthenticateResponse authenticateResponse = authenticateResponseCompletableFuture.get();

        assertOrder(authenticateResponse);
    }

    @Test
    void sign() {
        given();

        final SignResponse response = bankId.sign(
            SignRequest.builder()
                .personalNumber(personalNumber())
                .endUserIp("192.168.1.1")
                .userVisibleData(UserVisibleData.of("Hello"))
                .requirement(Requirement.builder().build())
                .build());

        assertOrder(response);
    }

    @Test
    @SneakyThrows
    void signAsync() {
        given();

        final CompletableFuture<SignResponse> response = bankId.signAsync(
            SignRequest.builder()
                .personalNumber(personalNumber())
                .endUserIp("192.168.1.1")
                .userVisibleData(UserVisibleData.of("Hello"))
                .requirement(Requirement.builder().build())
                .build());

        final SignResponse signResponse = response.get();

        assertOrder(signResponse);
    }

    @Test
    void collect() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
            .personalNumber(personalNumber())
            .endUserIp("192.168.1.1")
            .build());

        final CollectResponse collectResponse = bankId.collect(CollectRequest.builder()
            .orderRef(authenticateResponse.getOrderRef())
            .build());

        assertNotNull(collectResponse.getOrderRef());
        assertNotNull(collectResponse.getStatus());
        assertNotNull(collectResponse.getHintCode());
    }

    @Test
    @SneakyThrows
    void collectAsync() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
            .personalNumber(personalNumber())
            .endUserIp("192.168.1.1")
            .build());

        final CompletableFuture<CollectResponse> collectResponseCompletableFuture = bankId.collectAsync(CollectRequest.builder()
            .orderRef(authenticateResponse.getOrderRef())
            .build());

        final CollectResponse collectResponse = collectResponseCompletableFuture.get();

        assertNotNull(collectResponse.getOrderRef());
        assertNotNull(collectResponse.getStatus());
        assertNotNull(collectResponse.getHintCode());
    }

    @Test
    void cancel() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
            .personalNumber(personalNumber())
            .endUserIp("192.168.1.1")
            .build());

        final CancelResponse cancelResponse = bankId.cancel(CancelRequest.builder()
            .orderRef(authenticateResponse.getOrderRef()).build());
        assertNotNull(cancelResponse);
    }

    @Test
    @SneakyThrows
    void cancelAsync() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
            .personalNumber(personalNumber())
            .endUserIp("192.168.1.1")
            .build());

        final CompletableFuture<CancelResponse> cancelResponseCompletableFuture =
            bankId.cancelAsync(CancelRequest.builder()
                .orderRef(authenticateResponse.getOrderRef())
                .build());

        final CancelResponse cancelResponse = cancelResponseCompletableFuture.get();
        assertNotNull(cancelResponse);
    }

    private void given() {
        final InputStream pkcs12Resource = ResourceUtils.tryInputStreamFrom("test.p12");
        final InputStream caResource = ResourceUtils.tryInputStreamFrom("ca.test.crt");

        final Pkcs12 pkcs12 = Pkcs12.of(pkcs12Resource, "qwerty123");

        final Configuration configuration = Configuration.builder()
            .baseURL(URL_TEST)
            .pkcs12(pkcs12)
            .certificate(caResource)
            .build();

        bankId = BankId.of(configuration);
    }

    @BeforeEach
    private void setUp() {
        bankId = null;
    }

    private void assertOrder(final OrderResponse response) {
        // No errors should be returned
        assertTrue(response.optionalErrorCode().isEmpty());
        assertTrue(response.optionalDetails().isEmpty());

        // Validate the order
        assertNotNull(response.getOrderRef());
        assertNotNull(response.getAutoStartToken());
        assertNotNull(response.getQrStartSecret());
        assertNotNull(response.getQrStartToken());
    }


    private static String personalNumber() {
        return String.format("19%02d%02d%02d%02d",
            RandomUtils.between(1, 100),
            RandomUtils.between(1, 12),
            RandomUtils.between(1, 28),
            RandomUtils.between(1000, 9999));
    }

}