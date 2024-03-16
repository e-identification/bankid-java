package dev.nicklasw.bankid.client.response;

import java.util.StringJoiner;

/**
 * The abstract class representing the response from an order request.
 */
public abstract sealed class OrderResponse implements Response
    permits AuthenticateResponse, SignResponse {

    /**
     * Used to compile the start url.
     */
    protected String autoStartToken;

    /**
     * Used to collect the status of the order.
     */
    protected String orderRef;

    /**
     * Used to compute the animated QR code.
     */
    protected String qrStartToken;

    /**
     * Used to compute the animated QR code.
     */
    protected String qrStartSecret;

    OrderResponse(final String autoStartToken, final String orderRef, final String qrStartToken, final String qrStartSecret) {
        this.autoStartToken = autoStartToken;
        this.orderRef = orderRef;
        this.qrStartToken = qrStartToken;
        this.qrStartSecret = qrStartSecret;
    }

    public String getAutoStartToken() {
        return autoStartToken;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public String getQrStartToken() {
        return qrStartToken;
    }

    public String getQrStartSecret() {
        return qrStartSecret;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderResponse that)) {
            return false;
        }

        if (!getAutoStartToken().equals(that.getAutoStartToken())) {
            return false;
        }
        if (!getOrderRef().equals(that.getOrderRef())) {
            return false;
        }
        if (!getQrStartToken().equals(that.getQrStartToken())) {
            return false;
        }
        return getQrStartSecret().equals(that.getQrStartSecret());
    }

    @Override
    public int hashCode() {
        int result = getAutoStartToken().hashCode();
        result = 31 * result + getOrderRef().hashCode();
        result = 31 * result + getQrStartToken().hashCode();
        result = 31 * result + getQrStartSecret().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderResponse.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .add("autoStartToken='" + autoStartToken + "'")
            .add("orderRef='" + orderRef + "'")
            .add("qrStartToken='" + qrStartToken + "'")
            .add("qrStartSecret='" + qrStartSecret + "'")
            .toString();
    }
}