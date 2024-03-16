package dev.nicklasw.bankid.client.request;

import java.util.Objects;

/**
 * Request to cancel an ongoing sign or auth order.
 *
 * @param orderRef The orderRef retrieved from a order response
 */
public record CancelRequest(String orderRef) implements Request {

    /**
     * Creates a {@link CancelRequest}.
     *
     * @param orderRef The orderRef retrieved from a order response. Must not be {@code null}
     * @throws NullPointerException if {@code orderRef} is {@code null}
     */
    public CancelRequest(final String orderRef) {
        this.orderRef = Objects.requireNonNull(orderRef);
    }

    @Override
    public String getUri() {
        return "cancel";
    }

}
