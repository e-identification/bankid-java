package dev.nicklasw.bankid.exceptions;

import dev.nicklasw.bankid.client.response.Response;
import lombok.Getter;

@Getter
public class BankIdApiErrorException extends BankIdException {
    Response response;

    private BankIdApiErrorException(final Response response) {
        super(null);
        this.response = response;
    }

    public static BankIdApiErrorException of(final Response response) {
        return new BankIdApiErrorException(response);
    }

}
