package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.nicklasw.bankid.client.model.serializer.VisibleDataFormatConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonSerialize(using = VisibleDataFormatConverter.class)
public enum VisibleDataFormat {
    SIMPLE_MARKDOWN_V1("simpleMarkdownV1");

    private final String format;
}
