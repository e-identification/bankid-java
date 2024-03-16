package dev.eidentification.bankid.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * Represents a sign response.
 */
public final class SignResponse extends OrderResponse {

    @JsonCreator
    public SignResponse(
        @JsonProperty("orderRef") final String orderRef,
        @JsonProperty("autoStartToken") final String autoStartToken,
        @JsonProperty("qrStartToken") final String qrStartToken,
        @JsonProperty("qrStartSecret") final String qrStartSecret) {
        super(autoStartToken, orderRef, qrStartToken, qrStartSecret);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SignResponse.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .toString();
    }

}
