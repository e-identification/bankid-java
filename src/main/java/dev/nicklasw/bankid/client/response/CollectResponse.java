package dev.nicklasw.bankid.client.response;

import dev.nicklasw.bankid.client.model.CompletionData;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CollectResponse extends Response {
    String orderRef;
    String status;
    String hintCode;
    CompletionData completionData;
}
