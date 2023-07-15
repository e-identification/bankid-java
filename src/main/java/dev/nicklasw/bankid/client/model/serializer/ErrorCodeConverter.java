package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.nicklasw.bankid.client.model.ErrorCode;
import dev.nicklasw.bankid.internal.Internal;

import java.io.IOException;

@Internal
public class ErrorCodeConverter extends StdDeserializer<ErrorCode> {

    private ErrorCodeConverter() {
        super(ErrorCode.class);
    }

    @Override
    public ErrorCode deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if (node.isNull()) {
            return null;
        }

        return ErrorCode.of(node.asText());
    }
}
