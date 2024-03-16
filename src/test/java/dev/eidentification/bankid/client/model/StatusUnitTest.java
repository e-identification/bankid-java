package dev.eidentification.bankid.client.model;

import dev.eidentification.bankid.UnitTest;
import dev.eidentification.bankid.client.model.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class StatusUnitTest extends UnitTest {

    @ParameterizedTest
    @EnumSource(Status.class)
    void givenStatus_whenOf_thenExpectedStatus(final Status status) {
        var result = Status.of(status.getCode());

        Assertions.assertEquals(status, result);
    }

    @Test
    void givenUnknownStatusCode_whenOf_thenFailedStatus() {
        var result = Status.of("unknown");

        Assertions.assertEquals(Status.FAILED, result);
    }
}