package dev.nicklasw.bankid.client.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
public class OrderResponse extends Response {
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
}
