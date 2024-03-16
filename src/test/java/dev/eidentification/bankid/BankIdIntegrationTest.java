package dev.eidentification.bankid;

import dev.eidentification.bankid.client.model.PhoneRequirement;
import dev.eidentification.bankid.client.model.Requirement;
import dev.eidentification.bankid.client.model.UserVisibleData;
import dev.eidentification.bankid.client.model.enums.CallInitiator;
import dev.eidentification.bankid.client.request.AuthenticationRequest;
import dev.eidentification.bankid.client.request.CancelRequest;
import dev.eidentification.bankid.client.request.CollectRequest;
import dev.eidentification.bankid.client.request.PhoneAuthenticationRequest;
import dev.eidentification.bankid.client.request.PhoneSignRequest;
import dev.eidentification.bankid.client.request.SignRequest;
import dev.eidentification.bankid.client.response.AuthenticateResponse;
import dev.eidentification.bankid.client.response.CancelResponse;
import dev.eidentification.bankid.client.response.CollectResponse;
import dev.eidentification.bankid.client.response.OrderResponse;
import dev.eidentification.bankid.client.response.PhoneAuthenticateResponse;
import dev.eidentification.bankid.client.response.PhoneOrderResponse;
import dev.eidentification.bankid.client.response.PhoneSignResponse;
import dev.eidentification.bankid.client.response.SignResponse;
import dev.eidentification.bankid.client.utils.ResourceUtils;
import dev.eidentification.bankid.configuration.Configuration;
import dev.eidentification.bankid.configuration.Pkcs12;
import dev.eidentification.bankid.fixtures.utils.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static dev.eidentification.bankid.configuration.Configuration.URL_TEST;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BankIdIntegrationTest extends IntegrationTest {

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
    void authenticateAsync() throws ExecutionException, InterruptedException {
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
    void phoneAuthenticateAsync() throws ExecutionException, InterruptedException {
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
    void signAsync() throws ExecutionException, InterruptedException {
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
    void phoneSignAsync() throws ExecutionException, InterruptedException {
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

        final CollectResponse collectResponse = bankId.collect(new CollectRequest(authenticateResponse.getOrderRef()));

        assertNotNull(collectResponse.getOrderRef());
        assertNotNull(collectResponse.getStatus());
        assertNotNull(collectResponse.getHint());
    }

    @Test
    void collectAsync() throws ExecutionException, InterruptedException {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
            .endUserIp("192.168.1.1")
            .build());

        final CompletableFuture<CollectResponse> collectResponseCompletableFuture = bankId.collectAsync(new CollectRequest(authenticateResponse.getOrderRef()));

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

        final CancelResponse cancelResponse = bankId.cancel(new CancelRequest(authenticateResponse.getOrderRef()));
        assertNotNull(cancelResponse);
    }

    @Test
    void cancelAsync() throws ExecutionException, InterruptedException {
        given();

        final AuthenticateResponse authenticateResponse = bankId.authenticate(AuthenticationRequest.builder()
            .endUserIp("192.168.1.1")
            .build());

        final CompletableFuture<CancelResponse> cancelResponseCompletableFuture =
            bankId.cancelAsync(new CancelRequest(authenticateResponse.getOrderRef()));

        final CancelResponse cancelResponse = cancelResponseCompletableFuture.get();
        assertNotNull(cancelResponse);
    }

    private void given() {
        final InputStream pkcs12Resource = ResourceUtils.tryInputStreamFrom("test.p12");
        final InputStream caResource = ResourceUtils.tryInputStreamFrom("ca.test.crt");

        final Pkcs12 pkcs12 = Pkcs12.of(pkcs12Resource, "qwerty123");

        final Configuration configuration = Configuration.of(URL_TEST, pkcs12, caResource);

        bankId = BankId.of(configuration);
    }

    @BeforeEach
    public void setUp() {
        bankId = null;
    }

    private void assertOrder(final OrderResponse response) {
        // Validate the order
        assertNotNull(response.getOrderRef());
        assertNotNull(response.getAutoStartToken());
        assertNotNull(response.getQrStartSecret());
        assertNotNull(response.getQrStartToken());
    }

    private void assertOrder(final PhoneOrderResponse response) {
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