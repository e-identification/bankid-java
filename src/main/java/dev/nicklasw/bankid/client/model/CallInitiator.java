package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.nicklasw.bankid.client.model.serializer.CallInitiatorConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@JsonSerialize(using = CallInitiatorConverter.class)
@RequiredArgsConstructor
public enum CallInitiator {
    USER("user"),
    RP("RP");

    private final String callInitiatorValue;
}
