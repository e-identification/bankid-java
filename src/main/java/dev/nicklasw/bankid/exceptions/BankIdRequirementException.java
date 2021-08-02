package dev.nicklasw.bankid.exceptions;

public class BankIdRequirementException extends BankIdException {

    public BankIdRequirementException(final String errorDescription) {
        super(errorDescription, null);
    }
}
