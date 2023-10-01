package dev.nicklasw.bankid.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.nicklasw.bankid.client.model.CompletionData;
import dev.nicklasw.bankid.client.model.HintType;
import dev.nicklasw.bankid.client.model.Status;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CollectResponse extends Response {
    /**
     * The orderRef in question.
     */
    String orderRef;

    /**
     * The status of the order.
     * <p>
     * pending: The order is being processed. hintCode describes the status of the order.
     * complete: The order is complete. completionData holds user information.
     * failed: Something went wrong with the order. hintCode describes the error.
     */
    Status status;

    /**
     * RP should use the hintCode to provide the user with details and instructions and keep on calling collect until failed or complete.
     */
    @JsonProperty("hintCode")
    HintType hint;

    /**
     * A final state when an order is successful. The user has provided the security code and completed the order.
     * The completionData includes signature, user information and the OCSP response. RP should verify user information to proceed.
     * RP should retain completion data for future reference, compliance and audit purposes.
     */
    CompletionData completionData;
}
