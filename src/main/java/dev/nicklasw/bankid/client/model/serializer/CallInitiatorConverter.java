package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.nicklasw.bankid.client.model.CallInitiator;
import dev.nicklasw.bankid.internal.annotations.Internal;

import java.io.IOException;

@Internal
public class CallInitiatorConverter extends StdSerializer<CallInitiator> {

    CallInitiatorConverter() {
        super(CallInitiator.class);
    }

    @Override
    public void serialize(final CallInitiator visibleData, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(visibleData.getCallInitiatorValue());
    }
}
