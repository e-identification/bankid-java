package dev.eidentification.bankid.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.eidentification.bankid.client.model.CompletionData;
import dev.eidentification.bankid.client.model.HintType;
import dev.eidentification.bankid.client.model.enums.Status;

import java.util.StringJoiner;

/**
 * Represents the response of a collect operation.
 */
public final class CollectResponse implements Response {
    /**
     * The orderRef in question.
     */
    private final String orderRef;

    /**
     * The status of the order.
     * <p>
     * pending: The order is being processed. hintCode describes the status of the order.
     * complete: The order is complete. completionData holds user information.
     * failed: Something went wrong with the order. hintCode describes the error.
     */
    private final Status status;

    /**
     * RP should use the hintCode to provide the user with details and instructions and keep on calling collect until failed or complete.
     */
    private final HintType hint;

    /**
     * A final state when an order is successful. The user has provided the security code and completed the order.
     * The completionData includes signature, user information and the OCSP response. RP should verify user information to proceed.
     * RP should retain completion data for future reference, compliance and audit purposes.
     */
    private final CompletionData completionData;

    @JsonCreator
    public CollectResponse(
        @JsonProperty("orderRef") final String orderRef,
        @JsonProperty("status") final Status status,
        @JsonProperty("hintCode") final HintType hint,
        @JsonProperty("completionData") final CompletionData completionData) {
        this.orderRef = orderRef;
        this.status = status;
        this.hint = hint;
        this.completionData = completionData;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public Status getStatus() {
        return status;
    }

    public HintType getHint() {
        return hint;
    }

    public CompletionData getCompletionData() {
        return completionData;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CollectResponse that)) {
            return false;
        }

        if (!getOrderRef().equals(that.getOrderRef())) {
            return false;
        }
        if (getStatus() != that.getStatus()) {
            return false;
        }
        if (!getHint().equals(that.getHint())) {
            return false;
        }
        return getCompletionData().equals(that.getCompletionData());
    }

    @Override
    public int hashCode() {
        int result = getOrderRef().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getHint().hashCode();
        result = 31 * result + getCompletionData().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CollectResponse.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .add("orderRef='" + orderRef + "'")
            .add("status=" + status)
            .add("hint=" + hint)
            .add("completionData=" + completionData)
            .toString();
    }
}
