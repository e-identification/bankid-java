package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.nicklasw.bankid.client.model.ErrorCode;
import dev.nicklasw.bankid.client.model.HintCode;
import dev.nicklasw.bankid.client.model.HintType;
import dev.nicklasw.bankid.internal.annotations.Internal;

import java.io.IOException;

@Internal
public class HintTypeConverter extends StdDeserializer<HintType> {

    HintTypeConverter() {
        super(ErrorCode.class);
    }

    @Override
    public HintType deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if (node.isNull()) {
            return null;
        }

        final String errorCode = node.asText();

        return HintType.of(errorCode, HintCode.of(node.asText()));
    }
}
