package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.nicklasw.bankid.client.model.serializer.ErrorCodeConverter;

import java.util.Arrays;

@JsonDeserialize(using = ErrorCodeConverter.class)
public enum ErrorCode {
    /**
     * statusAlreadyInProgress is the hint for a order where an auth or sign request with personal number was sent,
     * but an order for the user is already in progress. The order is aborted. No order is created.
     * <p>
     * Details are found in details.
     */
    ALREADY_IN_PROGRESS("alreadyInProgress"),
    /**
     * status of a pending order. hintCode describes the status of the order.
     */
    STATUS_PENDING("pending"),
    /**
     * status of a failed order. hintCode describes the error.
     */
    STATUS_FAILED("failed"),
    /**
     * status of a complete order. completionData holds user information.
     */
    STATUS_COMPLETE("complete"),
    /**
     * hint for a request with invalid parameters.
     */
    INVALID_PARAMETERS("invalidParameters"),
    /**
     * hint for unknown error.
     */
    UNKNOWN("");

    private final String code;

    ErrorCode(final String code) {
        this.code = code;
    }

    public static ErrorCode of(final String code) {
        return Arrays.stream(values())
                .filter(it -> code.equals(it.code))
                .findFirst()
                .orElse(ErrorCode.UNKNOWN);
    }

}
