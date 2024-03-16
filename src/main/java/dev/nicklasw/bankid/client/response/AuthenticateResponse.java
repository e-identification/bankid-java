package dev.nicklasw.bankid.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * This is the final response class for Authentication extending from the OrderResponse base class.
 */
public final class AuthenticateResponse extends OrderResponse {

    /**
     * Creates an instance of AuthenticateResponse.
     *
     * @param orderRef       The order reference.
     * @param autoStartToken The auto start token.
     * @param qrStartToken   The QR start token.
     * @param qrStartSecret  The QR start secret.
     */
    @JsonCreator
    public AuthenticateResponse(
        @JsonProperty("orderRef") final String orderRef,
        @JsonProperty("autoStartToken") final String autoStartToken,
        @JsonProperty("qrStartToken") final String qrStartToken,
        @JsonProperty("qrStartSecret") final String qrStartSecret) {
        super(autoStartToken, orderRef, qrStartToken, qrStartSecret);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AuthenticateResponse.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .toString();
    }

}
