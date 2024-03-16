package dev.nicklasw.bankid.client.model.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.nicklasw.bankid.client.model.serializer.VisibleDataFormatSerializer;

@JsonSerialize(using = VisibleDataFormatSerializer.class)
public enum VisibleDataFormat {
    SIMPLE_MARKDOWN_V1("simpleMarkdownV1");

    private final String format;

    VisibleDataFormat(final String format) {
        this.format = format;
    }

    public String getFormat() {
        return this.format;
    }
}
