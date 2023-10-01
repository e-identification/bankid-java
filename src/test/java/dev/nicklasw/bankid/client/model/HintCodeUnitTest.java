package dev.nicklasw.bankid.client.model;

import dev.nicklasw.bankid.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class HintCodeUnitTest extends UnitTest {

    @ParameterizedTest
    @EnumSource(HintCode.class)
    void givenHintCode_whenOf_thenExpectedHintCode(final HintCode hintCode) {
        var result = HintCode.of(hintCode.getCode());

        Assertions.assertEquals(hintCode, result);
    }

    @Test
    void givenUnknownHintCode_whenOf_thenUnknownHintCode() {
        var result = HintCode.of("unknown");

        Assertions.assertEquals(HintCode.UNKNOWN, result);
    }

}