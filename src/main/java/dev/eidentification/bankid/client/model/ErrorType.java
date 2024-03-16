package dev.eidentification.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.eidentification.bankid.client.model.serializer.ErrorTypeSerializer;
import dev.eidentification.bankid.client.model.enums.ErrorCode;

@JsonDeserialize(using = ErrorTypeSerializer.class)
public record ErrorType(String codeValue, ErrorCode code) {

    public static ErrorType of(final String codeValue, final ErrorCode code) {
        return new ErrorType(codeValue, code);
    }

}
