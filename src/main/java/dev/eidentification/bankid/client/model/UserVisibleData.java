package dev.eidentification.bankid.client.model;

import dev.eidentification.bankid.exceptions.BankIdRequirementException;

import java.util.Objects;
import java.util.StringJoiner;

public final class UserVisibleData extends VisibleData {

    private UserVisibleData(final String content) {
        super(content);

        validateSelf();
    }

    /**
     * Creates an instance of UserVisibleData with the given content.
     *
     * @param content the content of the UserVisibleData
     * @return an instance of UserVisibleData
     * @throws NullPointerException if content is null
     * @throws BankIdRequirementException if the content is empty or exceeds the maximum length of 40,000 characters
     */
    public static UserVisibleData of(final String content) {
        Objects.requireNonNull(content);

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

    @Override
    public String toString() {
        return new StringJoiner(", ", UserVisibleData.class.getSimpleName() + "[", "]")
            .add("super='" + super.toString() + "'")
            .toString();
    }
}
