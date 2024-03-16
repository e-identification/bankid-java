package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.nicklasw.bankid.client.model.enums.CardReader;
import dev.nicklasw.bankid.internal.annotations.Internal;

import java.io.IOException;

@Internal
public class CardReaderSerializer extends StdSerializer<CardReader> {

    CardReaderSerializer() {
        super(CardReader.class);
    }

    @Override
    public void serialize(final CardReader cardReader, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(cardReader.getCode());
    }
}