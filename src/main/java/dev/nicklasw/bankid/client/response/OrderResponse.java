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
    protected String autoStartToken;
    protected String orderRef;
    protected String qrStartToken;
    protected String qrStartSecret;
}
