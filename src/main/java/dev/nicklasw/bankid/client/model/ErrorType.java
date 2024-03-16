package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.nicklasw.bankid.client.model.enums.ErrorCode;
import dev.nicklasw.bankid.client.model.serializer.ErrorTypeSerializer;

@JsonDeserialize(using = ErrorTypeSerializer.class)
public record ErrorType(String codeValue, ErrorCode code) {

    public static ErrorType of(final String codeValue, final ErrorCode code) {
        return new ErrorType(codeValue, code);
    }

}
