package dev.nicklasw.bankid.client.model.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.nicklasw.bankid.client.model.serializer.CallInitiatorSerializer;

/**
 * CallInitiator is an enumeration representing the possible call initiators in a phone authentication order.
 * It provides a method to retrieve the call initiator value.
 */
@JsonSerialize(using = CallInitiatorSerializer.class)
public enum CallInitiator {
    USER("user"),
    RP("RP");

    private final String callInitiatorValue;

    CallInitiator(final String callInitiatorValue) {
        this.callInitiatorValue = callInitiatorValue;
    }

    public String getCallInitiatorValue() {
        return this.callInitiatorValue;
    }
}
