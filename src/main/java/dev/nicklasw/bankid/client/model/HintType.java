package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.nicklasw.bankid.client.model.serializer.HintTypeConverter;
import lombok.Value;

@Value(staticConstructor = "of")
@JsonDeserialize(using = HintTypeConverter.class)
public class HintType {
    String codeValue;
    HintCode code;
}
