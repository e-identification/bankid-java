package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.nicklasw.bankid.client.model.serializer.VisibleDataConverter;
import lombok.Getter;

@Getter
@JsonSerialize(using = VisibleDataConverter.class)
public class VisibleData {
    String content;
}
