package dev.nicklasw.bankid.client.model;

import dev.nicklasw.bankid.exceptions.BankIdRequirementException;
import lombok.NonNull;

public final class UserVisibleData extends VisibleData {

    private UserVisibleData(final String content) {
        super(content);

        validateSelf();
    }

    /**
     * Creates a {@link UserVisibleData} of the given content.
     *
     * @param content must not be {@literal null}, empty or over 40 000 chars.
     * @throws BankIdRequirementException in case of invalid content.
     */
    public static UserVisibleData of(@NonNull final String content) {
        return new UserVisibleData(content);
    }

    private void validateSelf() {
        if (content.length() <= 0) {
            throw new BankIdRequirementException("UserVisibleData content cannot be empty");
        }

        if (content.length() > 40_000) {
            throw new BankIdRequirementException("UserVisibleData content cannot exceed 40_000 in length");
        }
    }
}
