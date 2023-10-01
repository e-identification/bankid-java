package dev.nicklasw.bankid.client.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CompletionData {
    /**
     * Information related to the user.
     */
    User user;

    /**
     * Information related to the device.
     */
    Device device;

    /**
     * The date the BankID was issued to the user.
     * The issue date of the ID expressed using ISO 8601 date format YYYY-MM-DD with a UTC time zone offset.
     */
    LocalDate bankIdIssueDate;

    /**
     * Information about extra verifications that were part of the transaction.
     * <p>
     * mrtd: Indicate if there was a check of the mrtd (machine readable travel document). Boolean.
     * <p>
     * True if the mrtd check was performed and passed.
     * False if the mrtd check was performed but failed.
     */
    boolean stepUp;

    /**
     * The signature as described in the BankID Signature Profile specification. String. Base64-encoded. XML signature.
     */
    String signature;

    /**
     * The OCSP response. String. Base64-encoded. The OCSP response is signed by a certificate that has the same issuer as the certificate being verified.
     * The OSCP response has an extension for Nonce. The nonce is calculated as:
     * <p>
     * SHA-1 hash over the base 64 XML signature encoded as UTF-8.
     * 12 random bytes is added after the hash.
     * The nonce is 32 bytes (20 + 12).
     */
    String ocspResponse;
}
