package dev.nicklasw.bankid.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * The PhoneSignResponse class represents the response from a phone sign order.
 *
 * @see PhoneOrderResponse
 */
public final class PhoneSignResponse extends PhoneOrderResponse {

    @JsonCreator
    PhoneSignResponse(@JsonProperty("orderRef") final String orderRef) {
        super(orderRef);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PhoneSignResponse.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .toString();
    }

}
