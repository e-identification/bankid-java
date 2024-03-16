package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.nicklasw.bankid.UnitTest;
import dev.nicklasw.bankid.client.model.enums.VisibleDataFormat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.stream.Stream;

import static org.mockito.Mockito.timeout;

class VisibleDataFormatSerializerUnitTest extends UnitTest {

    private final VisibleDataFormatSerializer converter = new VisibleDataFormatSerializer();

    @Mock
    private JsonGenerator jsonGenerator;
    @Mock
    private SerializerProvider serializerProvider;

    @ParameterizedTest
    @MethodSource("serializeMethodSource")
    void serialize(final VisibleDataFormat visibleDataFormat, final String expectedCallInitiatorValue) throws IOException {
        converter.serialize(visibleDataFormat, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, timeout(1))
            .writeString(expectedCallInitiatorValue);
    }

    private static Stream<Arguments> serializeMethodSource() {
        return Stream.of(
            Arguments.of(VisibleDataFormat.SIMPLE_MARKDOWN_V1, VisibleDataFormat.SIMPLE_MARKDOWN_V1.getFormat())
        );
    }
}