package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.nicklasw.bankid.client.model.VisibleDataFormat;
import dev.nicklasw.bankid.internal.annotations.Internal;

import java.io.IOException;

@Internal
public class VisibleDataFormatConverter extends StdSerializer<VisibleDataFormat> {

    VisibleDataFormatConverter() {
        super(VisibleDataFormat.class);
    }

    @Override
    public void serialize(final VisibleDataFormat visibleDataFormat, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider)
        throws IOException {
        jsonGenerator.writeString(visibleDataFormat.getFormat());
    }
}
