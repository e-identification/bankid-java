package dev.eidentification.bankid.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * Represents the response from a phone authentication order.
 * Extends {@link PhoneOrderResponse}.
 */
public final class PhoneAuthenticateResponse extends PhoneOrderResponse {

    @JsonCreator
    PhoneAuthenticateResponse(@JsonProperty("orderRef") final String orderRef) {
        super(orderRef);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PhoneAuthenticateResponse.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .toString();
    }

}
