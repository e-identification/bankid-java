package dev.eidentification.bankid.exceptions;

import java.net.http.HttpResponse;

/**
 * This class represents a specific exception to be used
 * when an unexpected API response is received in the interaction
 * with the BankId system. It extends {@link BankIdException} class
 * and inherits its behavior.
 * <p>
 * The class will store and provide the {@link HttpResponse.ResponseInfo}
 * and the {@link String} representation of the response body
 * for debug and error diagnosis.
 *
 * @see BankIdException
 */
public final class BankIdApiUnexpectedResponseException extends BankIdException {
    private final HttpResponse.ResponseInfo responseInfo;
    private final String responseBody;

    private BankIdApiUnexpectedResponseException(final HttpResponse.ResponseInfo responseInfo, final String responseBody, final Throwable cause) {
        super(cause);
        this.responseInfo = responseInfo;
        this.responseBody = responseBody;
    }

    /**
     * Creates a new instance of BankIdApiUnexpectedResponseException.
     *
     * @param responseInfo The response information associated with the exception.
     * @param responseBody The response body associated with the exception.
     * @param cause        The cause of the exception.
     * @return A new instance of BankIdApiUnexpectedResponseException.
     */
    public static BankIdApiUnexpectedResponseException of(final HttpResponse.ResponseInfo responseInfo, final String responseBody, final Throwable cause) {
        return new BankIdApiUnexpectedResponseException(responseInfo, responseBody, cause);
    }

    /**
     * Retrieves the response information associated with an exception thrown during interaction with the BankId system.
     *
     * @return The response information.
     */
    public HttpResponse.ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    /**
     * Retrieves the response body associated with the exception.
     *
     * @return The response body.
     */
    public String getResponseBody() {
        return this.responseBody;
    }
}
