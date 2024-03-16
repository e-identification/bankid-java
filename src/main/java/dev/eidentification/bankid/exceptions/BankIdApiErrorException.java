package dev.eidentification.bankid.exceptions;

import dev.eidentification.bankid.client.response.ErrorResponse;

/**
 * BankIdApiErrorException is a subclass of BankIdException that represents an error encountered
 * from the BankId API. It contains the error response from the API.
 */
public final class BankIdApiErrorException extends BankIdException {
    private final ErrorResponse response;

    private BankIdApiErrorException(final ErrorResponse response) {
        super("Encountered a error from the BankId API");

        this.response = response;
    }

    public static BankIdApiErrorException of(final ErrorResponse response) {
        return new BankIdApiErrorException(response);
    }

    /**
     * Retrieves the error response associated with the BankIdApiErrorException.
     *
     * @return The error response associated with this exception.
     */
    public ErrorResponse getResponse() {
        return this.response;
    }
}
