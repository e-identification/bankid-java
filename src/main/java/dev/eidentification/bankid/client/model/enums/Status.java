package dev.eidentification.bankid.client.model.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.eidentification.bankid.client.model.serializer.StatusSerializer;

import java.util.Arrays;

/**
 * Represents the status of an order.
 */
@JsonDeserialize(using = StatusSerializer.class)
public enum Status {
    /**
     * The order is being processed. hintCode describes the status of the order.
     */
    PENDING("pending"),
    /**
     * The order is complete. completionData holds user information.
     */
    COMPLETE("complete"),
    /**
     * Something went wrong with the order. hintCode describes the error.
     */
    FAILED("failed");

    private final String code;

    Status(final String code) {
        this.code = code;
    }

    public static Status of(final String code) {
        return Arrays.stream(values())
            .filter(it -> code.equals(it.code))
            .findFirst()
            .orElse(Status.FAILED);
    }

    public String getCode() {
        return this.code;
    }
}
