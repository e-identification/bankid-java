package dev.nicklasw.bankid.exceptions;

/**
 * BankIdRequirementException is a subclass of BankIdException that represents an exception
 * thrown when there is a requirement violation.
 */
public final class BankIdRequirementException extends BankIdException {

    public BankIdRequirementException(final String errorDescription) {
        super(errorDescription, null);
    }

}
