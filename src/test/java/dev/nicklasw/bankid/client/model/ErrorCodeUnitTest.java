package dev.nicklasw.bankid.client.model;

import dev.nicklasw.bankid.UnitTest;
import dev.nicklasw.bankid.client.model.enums.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ErrorCodeUnitTest extends UnitTest {

    @ParameterizedTest
    @EnumSource(ErrorCode.class)
    void givenErrorCode_whenOf_thenExpectedErrorCode(final ErrorCode errorCode) {
        var result = ErrorCode.of(errorCode.getCode());

        Assertions.assertEquals(errorCode, result);
    }

    @Test
    void givenUnknownErrorCode_whenOf_thenUnknownErrorCode() {
        var result = ErrorCode.of("unknown");

        Assertions.assertEquals(ErrorCode.UNKNOWN, result);
    }


}