package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.nicklasw.bankid.client.model.serializer.StatusConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
@JsonDeserialize(using = StatusConverter.class)
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

    public static Status of(final String code) {
        return Arrays.stream(values())
            .filter(it -> code.equals(it.code))
            .findFirst()
            .orElse(Status.FAILED);
    }

}
