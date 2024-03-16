package dev.eidentification.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.eidentification.bankid.internal.annotations.Internal;
import dev.eidentification.bankid.client.model.enums.ErrorCode;
import dev.eidentification.bankid.client.model.ErrorType;
import org.jspecify.annotations.Nullable;

import java.io.IOException;

@Internal
public class ErrorTypeSerializer extends StdDeserializer<ErrorType> {

    ErrorTypeSerializer() {
        super(ErrorCode.class);
    }

    @Override
    @Nullable
    public ErrorType deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if (node.isNull()) {
            return null;
        }

        final String errorCode = node.asText();

        return ErrorType.of(errorCode, ErrorCode.of(node.asText()));
    }
}
