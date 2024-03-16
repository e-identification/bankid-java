package dev.eidentification.bankid.client.model;

import dev.eidentification.bankid.UnitTest;
import dev.eidentification.bankid.exceptions.BankIdRequirementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserNonVisibleDataUnitTest extends UnitTest {

    @Test
    void givenValidContent_whenOf_thenExpectedContent() {
        var result = UserNonVisibleData.of("Hello");

        Assertions.assertEquals("Hello", result.getContent());
    }

    @Test
    void givenEmptyContent_whenOf_thenExpectedException() {
        Assertions.assertThrows(BankIdRequirementException.class, () -> UserNonVisibleData.of(""));
    }

    @Test
    void givenToLongContent_whenOf_thenExpectedException() {
        Assertions.assertThrows(BankIdRequirementException.class, () -> UserNonVisibleData.of("A".repeat(200_001)));
    }

}