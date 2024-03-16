package dev.eidentification.bankid.exceptions;

import org.jspecify.annotations.Nullable;

/**
 * The BankIdException class represents a generic exception that can be thrown
 * during interactions with the BankId system.
 *
 * This class is a subclass of the RuntimeException class, allowing it to be thrown
 * without being explicitly caught or declared in a method's throws clause.
 *
 * This class is declared as sealed, meaning that it can only be extended by the specified
 * subclasses: BankIdApiErrorException, BankIdApiUnexpectedResponseException, and BankIdRequirementException.
 *
 * @see BankIdApiErrorException
 * @see BankIdApiUnexpectedResponseException
 * @see BankIdRequirementException
 */
public sealed class BankIdException extends RuntimeException
    permits BankIdApiErrorException, BankIdApiUnexpectedResponseException, BankIdRequirementException {

    public BankIdException(final Throwable cause) {
        super(cause);
    }

    public BankIdException(final String message) {
        super(message);
    }

    public BankIdException(final String message, @Nullable final Throwable cause) {
        super(message, cause);
    }

}
