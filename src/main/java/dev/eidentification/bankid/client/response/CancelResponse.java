package dev.eidentification.bankid.client.response;

import java.util.StringJoiner;

/**
 * Represents a response from cancelling an ongoing sign or auth order.
 */
public final class CancelResponse implements Response {

    @Override
    public String toString() {
        return new StringJoiner(", ", CancelResponse.class.getSimpleName() + "[", "]")
            .add("super=" + super.toString())
            .toString();
    }

}
