package dev.nicklasw.bankid.exceptions;

import java.net.http.HttpResponse;

import lombok.Getter;

@Getter
public class BankIdApiUnexpectedResponseException extends BankIdException {
    HttpResponse.ResponseInfo responseInfo;
    String responseBody;

    private BankIdApiUnexpectedResponseException(final HttpResponse.ResponseInfo responseInfo, final String responseBody, final Throwable cause) {
        super(cause);
        this.responseInfo = responseInfo;
        this.responseBody = responseBody;
    }

    public static BankIdApiUnexpectedResponseException of(final HttpResponse.ResponseInfo responseInfo, final String responseBody, final Throwable cause) {
        return new BankIdApiUnexpectedResponseException(responseInfo, responseBody, cause);
    }

}
