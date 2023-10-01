package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.nicklasw.bankid.UnitTest;
import dev.nicklasw.bankid.client.model.CallInitiator;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.mockito.Mockito.timeout;

class CallInitiatorConverterUnitTest extends UnitTest {

    private final CallInitiatorConverter converter = new CallInitiatorConverter();

    @Mock
    private JsonGenerator jsonGenerator;
    @Mock
    private SerializerProvider serializerProvider;

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("serializeMethodSource")
    void serialize(final CallInitiator callInitiator, final String expectedCallInitiatorValue) {
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