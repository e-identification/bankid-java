package dev.nicklasw.bankid.client.model.enums;

import java.util.Arrays;

/**
 * Enumeration for representing various error codes that might be encountered from the BankID API.
 */
public enum ErrorCode {
    /**
     * statusAlreadyInProgress is the hint for a order where an auth or sign request with personal number was sent,
     * but an order for the user is already in progress. The order is aborted. No order is created.
     * <p>
     * Details are found in details.
     */
    ALREADY_IN_PROGRESS("alreadyInProgress"),
    /**
     * status of a pending order. hintCode describes the status of the order.
     */
    STATUS_PENDING("pending"),
    /**
     * status of a failed order. hintCode describes the error.
     */
    STATUS_FAILED("failed"),
    /**
     * status of a complete order. completionData holds user information.
     */
    STATUS_COMPLETE("complete"),
    /**
     * hint for a request with invalid parameters.
     */
    INVALID_PARAMETERS("invalidParameters"),
    /**
     * RP does not have access to the service.
     */
    UNAUTHORIZED("unauthorized"),
    /**
     * An erroneous URL inputStream was used.
     */
    NOT_FOUND("notFound"),
    /**
     * Only http method POST is allowed.
     */
    METHOD_NOT_ALLOWED("methodNotAllowed"),
    /**
     * It took too long time to transmit the request.
     */
    REQUEST_TIMEOUT("requestTimeout"),
    /**
     * Adding a "charset" parameter after 'application/json' is not allowed since the MIME type "application/json" has neither optional nor required parameters.
     */
    UNSUPPORTED_MEDIA_TYPE("unsupportedMediaType"),
    /**
     * Internal technical error in the BankID system.
     */
    INTERNAL_SERVER_ERROR("internalError"),
    /**
     * The service is temporarily unavailable.
     */
    MAINTENANCE("maintenance"),
    /**
     * New error codes may be introduced without prior notice.
     */
    UNKNOWN("");

    private final String code;

    ErrorCode(final String code) {
        this.code = code;
    }

    public static ErrorCode of(final String code) {
        return Arrays.stream(values())
            .filter(it -> code.equals(it.code))
            .findFirst()
            .orElse(ErrorCode.UNKNOWN);
    }

    public String getCode() {
        return this.code;
    }
}
