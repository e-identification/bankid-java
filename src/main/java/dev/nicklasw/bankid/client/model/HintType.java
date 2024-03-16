package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.nicklasw.bankid.client.model.enums.HintCode;
import dev.nicklasw.bankid.client.model.serializer.HintTypeSerializer;

/**
 * Represents a hint type that might be encountered from the BankID API.
 */
@JsonDeserialize(using = HintTypeSerializer.class)
public record HintType(String codeValue, HintCode code) {

    public static HintType of(final String codeValue, final HintCode code) {
        return new HintType(codeValue, code);
    }

}
