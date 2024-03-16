package dev.eidentification.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.eidentification.bankid.UnitTest;
import dev.eidentification.bankid.client.model.enums.CardReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.stream.Stream;

import static org.mockito.Mockito.timeout;

class CardReaderSerializerUnitTest extends UnitTest {

    private final CardReaderSerializer converter = new CardReaderSerializer();

    @Mock
    private JsonGenerator jsonGenerator;
    @Mock
    private SerializerProvider serializerProvider;

    @ParameterizedTest
    @MethodSource("serializeMethodSource")
    void serialize(final CardReader cardReader, final String expectedCode) throws IOException {
        converter.serialize(cardReader, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, timeout(1))
            .writeString(expectedCode);
    }

    private static Stream<Arguments> serializeMethodSource() {
        return Stream.of(
            Arguments.of(CardReader.CLASS1, CardReader.CLASS1.getCode()),
            Arguments.of(CardReader.CLASS2, CardReader.CLASS2.getCode())
        );
    }
}