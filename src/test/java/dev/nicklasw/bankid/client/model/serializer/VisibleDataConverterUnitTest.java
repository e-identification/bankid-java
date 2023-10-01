package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.nicklasw.bankid.UnitTest;
import dev.nicklasw.bankid.client.model.UserVisibleData;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.timeout;

class VisibleDataConverterUnitTest extends UnitTest {

    private final VisibleDataConverter converter = new VisibleDataConverter();

    @Mock
    private JsonGenerator jsonGenerator;
    @Mock
    private SerializerProvider serializerProvider;

    @Test
    @SneakyThrows
    void serialize() {
        var visibleData = UserVisibleData.of("Hello");

        converter.serialize(visibleData, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, timeout(1))
            .writeString("SGVsbG8=");
    }

}