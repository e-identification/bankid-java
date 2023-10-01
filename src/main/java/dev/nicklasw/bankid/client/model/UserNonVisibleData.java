package dev.nicklasw.bankid.client.model;

import dev.nicklasw.bankid.exceptions.BankIdRequirementException;

public class UserNonVisibleData extends VisibleData {

    private UserNonVisibleData(final String content) {
        super(content);

        validateSelf();
    }

    /**
     * Creates a {@link UserNonVisibleData} of the given content.
     *
     * @param content must not be {@literal null}, empty or over 200 000 chars.
     * @throws BankIdRequirementException in case of invalid content.
     */
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
