package dev.eidentification.bankid.client.model;

import dev.eidentification.bankid.exceptions.BankIdRequirementException;

import java.util.StringJoiner;

public final class UserNonVisibleData extends VisibleData {

    private UserNonVisibleData(final String content) {
        super(content);

        validateSelf();
    }

    /**
     * Creates an instance of UserNonVisibleData with the given content.
     *
     * @param content the content of the UserNonVisibleData
     * @return an instance of UserNonVisibleData
     * @throws BankIdRequirementException if the content is empty or exceeds the maximum length of 200,000 characters
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

    @Override
    public String toString() {
        return new StringJoiner(", ", UserNonVisibleData.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .toString();
    }

}
