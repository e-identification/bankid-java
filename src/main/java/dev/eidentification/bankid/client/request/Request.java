package dev.eidentification.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public sealed interface Request
    permits AuthenticationRequest, CancelRequest, CollectRequest, PhoneAuthenticationRequest, PhoneSignRequest, SignRequest {

    /**
     * Returns the URI associated with the request.
     *
     * @return The URI of the request.
     */
    @JsonIgnore
    String getUri();
}
