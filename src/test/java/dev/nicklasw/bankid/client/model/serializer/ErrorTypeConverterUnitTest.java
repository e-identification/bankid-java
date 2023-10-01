package dev.nicklasw.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import dev.nicklasw.bankid.UnitTest;
import dev.nicklasw.bankid.client.model.ErrorCode;
import dev.nicklasw.bankid.client.model.ErrorType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

class ErrorTypeConverterUnitTest extends UnitTest {

    private final ErrorTypeConverter converter = new ErrorTypeConverter();

    @Mock
    private JsonParser jsonParser;
    @Mock
    private ObjectCodec objectCodec;
    @Mock
    private JsonNode jsonNode;
    @Mock
    private DeserializationContext context;

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("deserializeMethodSource")
    void deserialize(final String codeValue, final ErrorType expected) {
        Mockito.when(jsonParser.getCodec())
            .thenReturn(objectCodec);
        Mockito.when(objectCodec.readTree(any()))
            .thenReturn(jsonNode);
        Mockito.when(jsonNode.asText())
            .thenReturn(codeValue);

        var result = converter.deserialize(jsonParser, context);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void givenUnknownCodeValue_whenDeserialize_thenExpectedErrorType() {
        Mockito.when(jsonParser.getCodec())
            .thenReturn(objectCodec);
        Mockito.when(objectCodec.readTree(any()))
            .thenReturn(jsonNode);
        Mockito.when(jsonNode.asText())
            .thenReturn("unknown");

        var result = converter.deserialize(jsonParser, context);

        Assertions.assertEquals(result.getCodeValue(), "unknown");
        Assertions.assertEquals(result.getCode(), ErrorCode.UNKNOWN);
    }

    private static Stream<Arguments> deserializeMethodSource() {
        return Arrays.stream(ErrorCode.values())
            .map(it -> Arguments.of(it.getCode(), ErrorType.of(it.getCode(), it)));
    }
}