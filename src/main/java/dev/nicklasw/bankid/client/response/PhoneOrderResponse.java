package dev.nicklasw.bankid.client.response;

import java.util.StringJoiner;

/**
 * The PhoneOrderResponse class represents the response from a phone order.
 * It is an abstract sealed class, meaning it has two subclasses: PhoneAuthenticateResponse and PhoneSignResponse.
 */
public abstract sealed class PhoneOrderResponse implements Response
    permits PhoneAuthenticateResponse, PhoneSignResponse {
    /**
     * Used to collect the status of the order.
     */
    protected String orderRef;

    PhoneOrderResponse(final String orderRef) {
        this.orderRef = orderRef;
    }

    public String getOrderRef() {
        return orderRef;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhoneOrderResponse that)) {
            return false;
        }

        return getOrderRef().equals(that.getOrderRef());
    }

    @Override
    public int hashCode() {
        return getOrderRef().hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PhoneOrderResponse.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .add("orderRef='" + orderRef + "'")
            .toString();
    }
}
