package dev.eidentification.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.eidentification.bankid.internal.annotations.Internal;
import dev.eidentification.bankid.client.model.VisibleData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Internal
public class VisibleDataSerializer extends StdSerializer<VisibleData> {

    VisibleDataSerializer() {
        super(VisibleData.class);
    }

    @Override
    public void serialize(final VisibleData visibleData, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(new String(Base64.getEncoder()
            .encode(visibleData.getContent()
                .getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
    }
}
