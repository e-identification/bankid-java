package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.nicklasw.bankid.client.model.serializer.ErrorTypeConverter;
import lombok.Value;

@Value(staticConstructor = "of")
@JsonDeserialize(using = ErrorTypeConverter.class)
public class ErrorType {
    String codeValue;
    ErrorCode code;
}
