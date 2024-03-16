package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.nicklasw.bankid.client.model.HintType;
import dev.nicklasw.bankid.client.model.enums.ErrorCode;
import dev.nicklasw.bankid.client.model.enums.HintCode;
import dev.nicklasw.bankid.internal.annotations.Internal;
import org.jspecify.annotations.Nullable;

import java.io.IOException;

@Internal
public class HintTypeSerializer extends StdDeserializer<HintType> {

    HintTypeSerializer() {
        super(ErrorCode.class);
    }

    @Nullable
    @Override
    public HintType deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if (node.isNull()) {
            return null;
        }

        final String hintCode = node.asText();

        return HintType.of(hintCode, HintCode.of(hintCode));
    }
}
