package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.nicklasw.bankid.client.model.serializer.VisibleDataSerializer;

import java.util.StringJoiner;

@JsonSerialize(using = VisibleDataSerializer.class)
public abstract sealed class VisibleData permits UserNonVisibleData, UserVisibleData {
    protected final String content;

    public VisibleData(final String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VisibleData.class.getSimpleName() + "[", "]")
            .add("content='" + content + "'")
            .toString();
    }
}
