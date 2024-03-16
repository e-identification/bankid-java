package dev.nicklasw.bankid.client.request;

import java.util.Objects;

/**
 * Request to collect the result of a sign or auth order using orderRef as reference.
 * RP should keep on calling collect every two seconds if status is pending.
 * RP must abort if status indicates failed. The user identity is returned when complete.
 *
 * @param orderRef The orderRef from the response from authentication or sign.
 */
public record CollectRequest(String orderRef) implements Request {

    /**
     * Creates a {@link CollectRequest}.
     *
     * @param orderRef The orderRef from the response from authentication or sign. Must not be {@code null}
     * @throws NullPointerException if {@code orderRef} is {@code null}
     */
    public CollectRequest(final String orderRef) {
        this.orderRef = Objects.requireNonNull(orderRef);
    }

    @Override
    public String getUri() {
        return "collect";
    }
}
