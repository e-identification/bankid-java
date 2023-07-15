package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.nicklasw.bankid.client.model.serializer.VisibleDataConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonSerialize(using = VisibleDataConverter.class)
@AllArgsConstructor
public abstract class VisibleData {
    protected final String content;
}
