package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.nicklasw.bankid.client.model.VisibleData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class VisibleDataConverter extends StdSerializer<VisibleData> {

    private VisibleDataConverter() {
        super(VisibleData.class);
    }

    @Override
    public void serialize(final VisibleData visibleData, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(new String(Base64.getEncoder()
                .encode(visibleData.getContent()
                        .getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
    }
}
