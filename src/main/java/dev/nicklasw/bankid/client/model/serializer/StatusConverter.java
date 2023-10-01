package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.nicklasw.bankid.client.model.Status;
import dev.nicklasw.bankid.internal.annotations.Internal;

import java.io.IOException;

@Internal
public class StatusConverter extends StdDeserializer<Status> {

    StatusConverter() {
        super(Status.class);
    }

    @Override
    public Status deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if (node.isNull()) {
            return null;
        }

        final String errorCode = node.asText();

        return Status.of(errorCode);
    }
}
