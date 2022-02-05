package dev.nicklasw.bankid.exceptions;

public class BankIdException extends RuntimeException {
    public BankIdException(final Throwable cause) {
        super(cause);
    }

    public BankIdException(final String message) {
        super(message);
    }

    public BankIdException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
