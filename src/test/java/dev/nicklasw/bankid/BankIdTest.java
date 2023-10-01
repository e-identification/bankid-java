package dev.nicklasw.bankid;

import dev.nicklasw.bankid.client.model.CallInitiator;
import dev.nicklasw.bankid.client.model.PhoneRequirement;
import dev.nicklasw.bankid.client.model.Requirement;
import dev.nicklasw.bankid.client.model.UserVisibleData;
import dev.nicklasw.bankid.client.request.AuthenticationRequest;
import dev.nicklasw.bankid.client.request.CancelRequest;
import dev.nicklasw.bankid.client.request.CollectRequest;
import dev.nicklasw.bankid.client.request.PhoneAuthenticationRequest;
import dev.nicklasw.bankid.client.request.PhoneSignRequest;
import dev.nicklasw.bankid.client.request.SignRequest;
import dev.nicklasw.bankid.client.response.AuthenticateResponse;
import dev.nicklasw.bankid.client.response.CancelResponse;
import dev.nicklasw.bankid.client.response.CollectResponse;
import dev.nicklasw.bankid.client.response.OrderResponse;
import dev.nicklasw.bankid.client.response.PhoneAuthenticateResponse;
import dev.nicklasw.bankid.client.response.PhoneOrderResponse;
import dev.nicklasw.bankid.client.response.PhoneSignResponse;
import dev.nicklasw.bankid.client.response.SignResponse;
import dev.nicklasw.bankid.client.utils.ResourceUtils;
import dev.nicklasw.bankid.configuration.Configuration;
import dev.nicklasw.bankid.configuration.Pkcs12;
import dev.nicklasw.bankid.utils.RandomUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

import static dev.nicklasw.bankid.configuration.Configuration.URL_TEST;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankIdTest {

    private BankId bankId;

    @Test
    void authenticate() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(
            AuthenticationRequest.builder()
                .endUserIp("192.168.1.1")
                .requirement(Requirement.builder()
                    .personalNumber(personalNumber())
                    .build())
                .build());

        assertOrder(authenticateResponse);
    }

    @Test
    @SneakyThrows
    void authenticateAsync() {
        given();

        final CompletableFuture<AuthenticateResponse> authenticateResponseCompletableFuture = bankId.authenticateAsync(
            AuthenticationRequest.builder()
                .endUserIp("192.168.1.1")
                .requirement(Requirement.builder()
                    .personalNumber(personalNumber())
                    .build())
                .build());

        final AuthenticateResponse authenticateResponse = authenticateResponseCompletableFuture.get();

        assertOrder(authenticateResponse);
    }

    @Test
    void phoneAuthenticate() {
        given();

        final PhoneAuthenticateResponse phoneAuthenticateResponse = bankId.phoneAuthenticate(
            PhoneAuthenticationRequest.builder()
                .personalNumber(personalNumber())
                .callInitiator(CallInitiator.USER)
                .build());

        assertOrder(phoneAuthenticateResponse);
    }

    @Test
    @SneakyThrows
    void phoneAuthenticateAsync() {
        given();

        final CompletableFuture<PhoneAuthenticateResponse> authenticateResponseCompletableFuture = bankId.phoneAuthenticateAsync(
            PhoneAuthenticationRequest.builder()
                .personalNumber(personalNumber())
                .callInitiator(CallInitiator.USER)
                .build());

        final PhoneAuthenticateResponse phoneAuthenticateResponse = authenticateResponseCompletableFuture.get();

        assertOrder(phoneAuthenticateResponse);
    }

    @Test
    void sign() {
        given();

        final SignResponse response = bankId.sign(
            SignRequest.builder()
                .endUserIp("192.168.1.1")
                .userVisibleData(UserVisibleData.of("Hello"))
                .requirement(Requirement.builder()
                    .personalNumber(personalNumber())
                    .build())
                .build());

        assertOrder(response);
    }

    @Test
    @SneakyThrows
    void signAsync() {
        given();

        final CompletableFuture<SignResponse> response = bankId.signAsync(
            SignRequest.builder()
                .endUserIp("192.168.1.1")
                .userVisibleData(UserVisibleData.of("Hello"))
                .requirement(Requirement.builder()
                    .personalNumber(personalNumber())
                    .build())
                .build());

        final SignResponse signResponse = response.get();

        assertOrder(signResponse);
    }

    @Test
    void phoneSign() {
        given();

        final PhoneSignResponse response = bankId.phoneSign(
            PhoneSignRequest.builder()
                .personalNumber(personalNumber())
                .callInitiator(CallInitiator.RP)
                .userVisibleData(UserVisibleData.of("Hello"))
                .requirement(PhoneRequirement.builder()
                    .pinCode(true)
                    .build())
                .build());

        assertOrder(response);
    }

    @Test
    @SneakyThrows
    void phoneSignAsync() {
        given();

        final CompletableFuture<PhoneSignResponse> response = bankId.phoneSignAsync(
            PhoneSignRequest.builder()
                .personalNumber(personalNumber())
                .callInitiator(CallInitiator.RP)
                .userVisibleData(UserVisibleData.of("Hello"))
                .requirement(PhoneRequirement.builder()
                    .pinCode(true)
                    .build())
                .build());

        final PhoneSignResponse phoneSignResponse = response.get();

        assertOrder(phoneSignResponse);
    }

    @Test
    void collect() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
            .endUserIp("192.168.1.1")
            .build());

        final CollectResponse collectResponse = bankId.collect(CollectRequest.builder()
            .orderRef(authenticateResponse.getOrderRef())
            .build());

        assertNotNull(collectResponse.getOrderRef());
        assertNotNull(collectResponse.getStatus());
        assertNotNull(collectResponse.getHint());
    }

    @Test
    @SneakyThrows
    void collectAsync() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
            .endUserIp("192.168.1.1")
            .build());

        final CompletableFuture<CollectResponse> collectResponseCompletableFuture = bankId.collectAsync(CollectRequest.builder()
            .orderRef(authenticateResponse.getOrderRef())
            .build());

        final CollectResponse collectResponse = collectResponseCompletableFuture.get();

        assertNotNull(collectResponse.getOrderRef());
        assertNotNull(collectResponse.getStatus());
        assertNotNull(collectResponse.getHint());
    }

    @Test
    void cancel() {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
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
        assertTrue(response.optionalErrorType().isEmpty());
        assertTrue(response.optionalDetails().isEmpty());

        // Validate the order
        assertNotNull(response.getOrderRef());
        assertNotNull(response.getAutoStartToken());
        assertNotNull(response.getQrStartSecret());
        assertNotNull(response.getQrStartToken());
    }

    private void assertOrder(final PhoneOrderResponse response) {
        // No errors should be returned
        assertTrue(response.optionalErrorType().isEmpty());
        assertTrue(response.optionalDetails().isEmpty());

        // Validate the order
        assertNotNull(response.getOrderRef());
    }

    private static String personalNumber() {
        return String.format("19%02d%02d%02d%02d",
            RandomUtils.between(1, 100),
            RandomUtils.between(1, 12),
            RandomUtils.between(1, 28),
            RandomUtils.between(1000, 9999));
    }

}