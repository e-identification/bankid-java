package dev.nicklasw.bankid;

import dev.nicklasw.bankid.client.request.PhoneAuthenticationRequest;
import dev.nicklasw.bankid.client.request.PhoneSignRequest;
import dev.nicklasw.bankid.client.response.PhoneAuthenticateResponse;
import dev.nicklasw.bankid.client.response.PhoneSignResponse;
import dev.nicklasw.bankid.internal.http.Client;
import dev.nicklasw.bankid.client.request.AuthenticationRequest;
import dev.nicklasw.bankid.client.request.CancelRequest;
import dev.nicklasw.bankid.client.request.CollectRequest;
import dev.nicklasw.bankid.client.request.SignRequest;
import dev.nicklasw.bankid.client.response.AuthenticateResponse;
import dev.nicklasw.bankid.client.response.CancelResponse;
import dev.nicklasw.bankid.client.response.CollectResponse;
import dev.nicklasw.bankid.client.response.SignResponse;
import dev.nicklasw.bankid.configuration.Configuration;
import dev.nicklasw.bankid.exceptions.BankIdException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BankId {

    private final Client client;

    /**
     * Returns a {@link BankId} with the given configuration.
     *
     * @param configuration the configuration
     * @return an {@link BankId} instance
     */
    public static BankId of(final Configuration configuration) {
        return new BankId(Client.of(configuration));
    }

    /**
     * Initiates an authentication order.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the authentication request
     * @throws BankIdException in case of an error
     */
    public AuthenticateResponse authenticate(@NonNull final AuthenticationRequest request) {
        return client.sendRequest(request, AuthenticateResponse.class);
    }

    /**
     * Initiates an authentication order asynchronous.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     * <p>
     * Propagates {@link BankIdException} in case of error.
     *
     * @param request the authentication request
     */
    public CompletableFuture<AuthenticateResponse> authenticateAsync(@NonNull final AuthenticationRequest request) {
        return client.sendRequestAsync(request, AuthenticateResponse.class);
    }

    /**
     * Initiates an authentication order when the user is talking to the RP over the phone.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the authentication request
     * @throws BankIdException in case of an error
     */
    public PhoneAuthenticateResponse phoneAuthenticate(@NonNull final PhoneAuthenticationRequest request) {
        return client.sendRequest(request, PhoneAuthenticateResponse.class);
    }

    /**
     * Initiates an authentication order asynchronous when the user is talking to the RP over the phone.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the authentication request
     * @throws BankIdException in case of an error
     */
    public CompletableFuture<PhoneAuthenticateResponse> phoneAuthenticateAsync(@NonNull final PhoneAuthenticationRequest request) {
        return client.sendRequestAsync(request, PhoneAuthenticateResponse.class);
    }

    /**
     * Initiates a sign order.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the sign request
     * @throws BankIdException in case of an error
     */
    public SignResponse sign(@NonNull final SignRequest request) {
        return client.sendRequest(request, SignResponse.class);
    }

    /**
     * Initiates a sign order asynchronous.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     * <p>
     * Propagates {@link BankIdException} in case of error.
     *
     * @param request the sign request
     */
    public CompletableFuture<SignResponse> signAsync(@NonNull final SignRequest request) {
        return client.sendRequestAsync(request, SignResponse.class);
    }


    /**
     * Initiates a sign order over the phone.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the sign request
     * @throws BankIdException in case of an error
     */
    public PhoneSignResponse phoneSign(@NonNull final PhoneSignRequest request) {
        return client.sendRequest(request, PhoneSignResponse.class);
    }

    /**
     * Initiates a sign order over the phone.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the sign request
     * @throws BankIdException in case of an error
     */
    public CompletableFuture<PhoneSignResponse> phoneSignAsync(@NonNull final PhoneSignRequest request) {
        return client.sendRequestAsync(request, PhoneSignResponse.class);
    }

    /**
     * Collects the result of a sign or auth order suing the orderRef as reference.
     * <p>
     * RP should keep calling collect every two seconds as long as status indicates pending.
     * RP must abort if status indicates failed. The user identity is returned when complete.
     *
     * @param request the collect request
     * @throws BankIdException in case of an error
     */
    public CollectResponse collect(@NonNull final CollectRequest request) {
        return client.sendRequest(request, CollectResponse.class);
    }

    /**
     * Collects the result of a sign or auth order suing the orderRef as reference asynchronous.
     * <p>
     * RP should keep calling collect every two seconds as long as status indicates pending.
     * RP must abort if status indicates failed. The user identity is returned when complete.
     * <p>
     * Propagates {@link BankIdException} in case of error.
     *
     * @param request the collect request
     */
    public CompletableFuture<CollectResponse> collectAsync(@NonNull final CollectRequest request) {
        return client.sendRequestAsync(request, CollectResponse.class);
    }

    /**
     * Cancels an ongoing sign or auth order.
     * <p>
     * This is typically used if the user cancels the order in your service or app.
     *
     * @param request the cancel request
     * @throws BankIdException in case of an error
     */
    public CancelResponse cancel(@NonNull final CancelRequest request) {
        return client.sendRequest(request, CancelResponse.class);
    }

    /**
     * Cancels an ongoing sign or auth order.
     * <p>
     * This is typically used if the user cancels the order in your service or app.
     * <p>
     * Propagates {@link BankIdException} in case of error.
     *
     * @param request the cancel request
     */
    public CompletableFuture<CancelResponse> cancelAsync(@NonNull final CancelRequest request) {
        return client.sendRequestAsync(request, CancelResponse.class);
    }

}
