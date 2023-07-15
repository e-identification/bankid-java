package dev.nicklasw.bankid.client.model;

import dev.nicklasw.bankid.exceptions.BankIdRequirementException;
import lombok.NonNull;

public class UserVisibleData extends VisibleData {

    private UserVisibleData(final String content) {
        super(content);

        validateSelf();
    }

    public static UserVisibleData of(@NonNull final String content) {
        return new UserVisibleData(content);
    }

    private void validateSelf() {
        if (content.length() <= 0) {
            throw new BankIdRequirementException("UserVisibleData content cannot be empty");
        }

        if (content.length() > 40_000) {
            throw new BankIdRequirementException("UserVisibleData content cannot exceed 200_000 in length");
        }
    }
}
