package dev.nicklasw.bankid.client.model;

import dev.nicklasw.bankid.exceptions.BankIdRequirementException;

public class UserNonVisibleData extends VisibleData {

    private UserNonVisibleData(final String content) {
        super(content);

        validateSelf();
    }

    public static UserNonVisibleData of(final String content) {
        return new UserNonVisibleData(content);
    }

    private void validateSelf() {
        if (content.length() <= 0) {
            throw new BankIdRequirementException("UserNonVisibleData content cannot be empty");
        }

        if (content.length() > 200_000) {
            throw new BankIdRequirementException("UserNonVisibleData content cannot exceed 200_000 in length");
        }
    }
}
