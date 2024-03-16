package dev.nicklasw.bankid;

import dev.nicklasw.bankid.client.request.AuthenticationRequest;
import dev.nicklasw.bankid.client.request.CancelRequest;
import dev.nicklasw.bankid.client.request.CollectRequest;
import dev.nicklasw.bankid.client.request.PhoneAuthenticationRequest;
import dev.nicklasw.bankid.client.request.PhoneSignRequest;
import dev.nicklasw.bankid.client.request.SignRequest;
import dev.nicklasw.bankid.client.response.AuthenticateResponse;
import dev.nicklasw.bankid.client.response.CancelResponse;
import dev.nicklasw.bankid.client.response.CollectResponse;
import dev.nicklasw.bankid.client.response.PhoneAuthenticateResponse;
import dev.nicklasw.bankid.client.response.PhoneSignResponse;
import dev.nicklasw.bankid.client.response.SignResponse;
import dev.nicklasw.bankid.configuration.Configuration;
import dev.nicklasw.bankid.exceptions.BankIdApiErrorException;
import dev.nicklasw.bankid.exceptions.BankIdApiUnexpectedResponseException;
import dev.nicklasw.bankid.exceptions.BankIdException;
import dev.nicklasw.bankid.internal.http.Client;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * The {@code BankId} class provides methods to initiate and process BankID authentication and signing orders.
 * Most methods initiate an order and respond with {@code orderRef} and {@code autoStartToken}.
 * Use the {@link #collect(CollectRequest)} or {@link #collectAsync(CollectRequest)} methods to query the status of the order.
 * <p>
 * Each method in the class comes in two forms: synchronous and asynchronous.
 * The latter performs operation in a non-blocking manner and returns {@link java.util.concurrent.CompletableFuture}.
 *
 * @see java.util.concurrent.CompletableFuture
 */
public final class BankId {

    private final Client client;

    private BankId(final Client client) {
        this.client = client;
    }

    /**
     * Returns a {@link BankId} with the given configuration.
     *
     * @param configuration the configuration
     * @return an {@link BankId} instance
     * @throws NullPointerException if {@code configuration} is {@code null}
     * @throws BankIdException      if an error occurs during initialization
     */
    public static BankId of(final Configuration configuration) {
        Objects.requireNonNull(configuration);

        return new BankId(Client.of(configuration));
    }

    /**
     * Initiates an authentication order.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the authentication request
     * @return the authentication response
     * @throws NullPointerException                 if {@code request} is {@code null}.
     * @throws BankIdException                      in case of an unexpected error.
     * @throws BankIdApiErrorException              in case of an api error.
     * @throws BankIdApiUnexpectedResponseException in case of an unexpected api error.
     */
    public AuthenticateResponse authenticate(final AuthenticationRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequest(request, AuthenticateResponse.class);
    }

    /**
     * Initiates an authentication order asynchronous.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     * <p>
     * Propagates {@link BankIdException} if an error occurred during the authentication.
     *
     * @param request the authentication request
     * @return a {@link CompletableFuture} that represents the asynchronous authentication operation
     * @throws NullPointerException if {@code request} is {@code null}.
     */
    public CompletableFuture<AuthenticateResponse> authenticateAsync(final AuthenticationRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequestAsync(request, AuthenticateResponse.class);
    }

    /**
     * Initiates an authentication order when the user is talking to the RP over the phone.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the authentication request
     * @return the phone authentication response
     * @throws NullPointerException                 if {@code request} is {@code null}.
     * @throws BankIdException                      in case of an unexpected error.
     * @throws BankIdApiErrorException              in case of an api error.
     * @throws BankIdApiUnexpectedResponseException in case of an unexpected api error.
     */
    public PhoneAuthenticateResponse phoneAuthenticate(final PhoneAuthenticationRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequest(request, PhoneAuthenticateResponse.class);
    }

    /**
     * Initiates an authentication order asynchronous when the user is talking to the RP over the phone.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     * <p>
     * Propagates {@link BankIdException} if an error occurred during the authentication.
     *
     * @param request the authentication request
     * @return a {@link CompletableFuture} that represents the asynchronous phone authentication operation
     * @throws NullPointerException if {@code request} is {@code null}.
     */
    public CompletableFuture<PhoneAuthenticateResponse> phoneAuthenticateAsync(final PhoneAuthenticationRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequestAsync(request, PhoneAuthenticateResponse.class);
    }

    /**
     * Initiates a sign order.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the sign request
     * @return the sign response
     * @throws NullPointerException                 if {@code request} is {@code null}.
     * @throws BankIdException                      in case of an unexpected error.
     * @throws BankIdApiErrorException              in case of an api error.
     * @throws BankIdApiUnexpectedResponseException in case of an unexpected api error.
     */
    public SignResponse sign(final SignRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequest(request, SignResponse.class);
    }

    /**
     * Initiates a sign order asynchronous.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     * <p>
     * Propagates {@link BankIdException} if an error occurred during the sign.
     *
     * @param request the sign request
     * @return a {@link CompletableFuture} that represents the asynchronous sign operation
     * @throws NullPointerException if {@code request} is {@code null}.
     */
    public CompletableFuture<SignResponse> signAsync(final SignRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequestAsync(request, SignResponse.class);
    }

    /**
     * Initiates a sign order over the phone.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     *
     * @param request the sign request
     * @return the phone sign response
     * @throws NullPointerException                 if {@code request} is {@code null}.
     * @throws BankIdException                      in case of an unexpected error.
     * @throws BankIdApiErrorException              in case of an api error.
     * @throws BankIdApiUnexpectedResponseException in case of an unexpected api error.
     */
    public PhoneSignResponse phoneSign(final PhoneSignRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequest(request, PhoneSignResponse.class);
    }

    /**
     * Initiates a sign order over the phone.
     * <p>
     * Use the collect method to query the status of the order.
     * If the request is successful, the orderRef and autoStartToken is returned.
     * <p>
     * Propagates {@link BankIdException} if an error occurred during the phone sign.
     *
     * @param request the sign request
     * @return a {@link CompletableFuture} that represents the asynchronous phone sign operation
     * @throws NullPointerException if {@code request} is {@code null}.
     */
    public CompletableFuture<PhoneSignResponse> phoneSignAsync(final PhoneSignRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequestAsync(request, PhoneSignResponse.class);
    }

    /**
     * Collects the result of a sign or auth order suing the orderRef as reference.
     * <p>
     * RP should keep calling collect every two seconds as long as status indicates pending.
     * RP must abort if status indicates failed. The user identity is returned when complete.
     *
     * @param request the collect request
     * @return the collect response
     * @throws NullPointerException                 if {@code request} is {@code null}.
     * @throws BankIdException                      in case of an unexpected error.
     * @throws BankIdApiErrorException              in case of an api error.
     * @throws BankIdApiUnexpectedResponseException in case of an unexpected api error.
     */
    public CollectResponse collect(final CollectRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequest(request, CollectResponse.class);
    }

    /**
     * Collects the result of a sign or auth order suing the orderRef as reference asynchronous.
     * <p>
     * RP should keep calling collect every two seconds as long as status indicates pending.
     * RP must abort if status indicates failed. The user identity is returned when complete.
     * <p>
     * Propagates {@link BankIdException} if an error occurred during the collect.
     *
     * @param request the collect request
     * @return a {@link CompletableFuture} that represents the asynchronous collect operation
     * @throws NullPointerException if {@code request} is {@code null}.
     */
    public CompletableFuture<CollectResponse> collectAsync(final CollectRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequestAsync(request, CollectResponse.class);
    }

    /**
     * Cancels an ongoing sign or auth order.
     * <p>
     * This is typically used if the user cancels the order in your service or app.
     *
     * @param request the cancel request
     * @return the cancel response
     * @throws NullPointerException                 if {@code request} is {@code null}.
     * @throws BankIdException                      in case of an unexpected error.
     * @throws BankIdApiErrorException              in case of an api error.
     * @throws BankIdApiUnexpectedResponseException in case of an unexpected api error.
     */
    public CancelResponse cancel(final CancelRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequest(request, CancelResponse.class);
    }

    /**
     * Cancels an ongoing sign or auth order.
     * <p>
     * This is typically used if the user cancels the order in your service or app.
     * <p>
     * Propagates {@link BankIdException} if an error occurred during the cancel.
     *
     * @param request the cancel request
     * @return a {@link CompletableFuture} that represents the asynchronous cancel operation
     * @throws NullPointerException if {@code request} is {@code null}.
     */
    public CompletableFuture<CancelResponse> cancelAsync(final CancelRequest request) {
        Objects.requireNonNull(request);

        return client.sendRequestAsync(request, CancelResponse.class);
    }

}
