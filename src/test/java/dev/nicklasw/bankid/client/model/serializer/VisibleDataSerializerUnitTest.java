package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.nicklasw.bankid.UnitTest;
import dev.nicklasw.bankid.client.model.UserVisibleData;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.timeout;

class VisibleDataSerializerUnitTest extends UnitTest {

    private final VisibleDataSerializer converter = new VisibleDataSerializer();

    @Mock
    private JsonGenerator jsonGenerator;
    @Mock
    private SerializerProvider serializerProvider;

    @Test
    void serialize() throws IOException {
        var visibleData = UserVisibleData.of("Hello");

        converter.serialize(visibleData, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, timeout(1))
            .writeString("SGVsbG8=");
    }

}