package dev.eidentification.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.eidentification.bankid.UnitTest;
import dev.eidentification.bankid.client.model.enums.CallInitiator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.stream.Stream;

import static org.mockito.Mockito.timeout;

class CallInitiatorSerializerUnitTest extends UnitTest {

    private final CallInitiatorSerializer converter = new CallInitiatorSerializer();

    @Mock
    private JsonGenerator jsonGenerator;
    @Mock
    private SerializerProvider serializerProvider;

    @ParameterizedTest
    @MethodSource("serializeMethodSource")
    void serialize(final CallInitiator callInitiator, final String expectedCallInitiatorValue) throws IOException {
        converter.serialize(callInitiator, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, timeout(1))
            .writeString(expectedCallInitiatorValue);
    }

    private static Stream<Arguments> serializeMethodSource() {
        return Stream.of(
            Arguments.of(CallInitiator.USER, CallInitiator.USER.getCallInitiatorValue()),
            Arguments.of(CallInitiator.RP, CallInitiator.RP.getCallInitiatorValue())
        );
    }
}