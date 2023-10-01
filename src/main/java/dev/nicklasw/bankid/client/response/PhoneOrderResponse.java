package dev.nicklasw.bankid.client.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
public class PhoneOrderResponse extends Response {
    /**
     * Used to collect the status of the order.
     */
    protected String orderRef;
}
