package dev.eidentification.bankid.client.model.enums;

import java.util.Arrays;

/**
 * Enumeration for representing various hint codes that might be encountered from the BankID API.
 */
public enum HintCode {

    /**
     * Order is pending. The BankID app has not yet received the order. The hintCode will later change to noClient, started or userSign.
     */
    OUTSTANDING_TRANSACTION("outstandingTransaction"),

    /**
     * Order is pending. The client has not yet received the order.
     */
    NO_CLIENT("noClient"),

    /**
     * Order is pending. A BankID client has launched with autostarttoken but a usable ID has not yet been found in the client.
     * <p>
     * When the client launches there may be a short delay until all IDs are registered.
     * <p>
     * The user may not have any usable IDs, or is yet to insert their smart card.
     */
    STARTED("started"),

    /**
     * Order is pending. A client has launched and received the order but additional steps for providing MRTD information is required to proceed with the order.
     */
    USER_MRTD("userMrtd"),

    /**
     * Order is waiting for the user to confirm that they have received this order while in a call with the RP.
     */
    USER_CALL_CONFIRM("userCallConfirm"),

    /**
     * Order is pending. The BankID client has received the order.
     */
    USER_SIGN("userSign"),

    /**
     * The order has expired. The BankID security app/program did not launch, the user did not finalize the signing or the RP called collect too late.
     */
    EXPIRED_TRANSACTION("expiredTransaction"),

    /**
     * This error is returned if:
     * <p>
     * The user has entered the wrong PIN code too many times. The BankID cannot be used.
     * The user’s BankID is blocked.
     * The user’s BankID is invalid.
     */
    CERTIFICATE_ERROR("certificateErr"),

    /**
     * The order was cancelled by the user. userCancel may also be returned in some rare cases related to other user interactions.
     */
    USER_CANCEL("userCancel"),

    /**
     * The order was cancelled. The system received a new order for the user.
     */
    CANCELLED("cancelled"),

    /**
     * The user did not provide their ID or the client did not launch within a certain time limit. Potential causes are:
     * <p>
     * RP did not use autoStartToken when launching the BankID security app. RP must correct this in their implementation.
     * Client software was not installed or other problem with the user’s device.
     */
    START_FAILED("startFailed"),

    /**
     * The order was cancelled because the user indicated in the app that they are not in a call with the RP about the order.
     */
    USER_DECLINED_CALL("userDeclinedCall"),

    /**
     * New hint code may be introduced without prior notice.
     */
    UNKNOWN("");

    private final String code;

    HintCode(final String code) {
        this.code = code;
    }

    public static HintCode of(final String code) {
        return Arrays.stream(values())
            .filter(it -> code.equals(it.code))
            .findFirst()
            .orElse(HintCode.UNKNOWN);
    }

    public String getCode() {
        return this.code;
    }
}
