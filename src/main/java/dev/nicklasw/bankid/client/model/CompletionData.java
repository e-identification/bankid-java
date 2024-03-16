package dev.nicklasw.bankid.client.model;

import java.time.LocalDate;

/**
 * A final state when an order is successful. The user has provided the security code and completed the order.
 * The completionData includes signature, user information and the OCSP response.
 * RP should verify user information to proceed.
 * RP should retain completion data for future reference, compliance and audit purposes.
 *
 * @param user            Information related to the user.
 * @param device          Information related to the device.
 * @param bankIdIssueDate The date the BankID was issued to the user.
 *                        The issue date of the ID expressed using ISO 8601 date format YYYY-MM-DD with a UTC time zone offset.
 * @param stepUp          Information about extra verifications that were part of the transaction.
 *                        mrtd: Indicate if there was a check of the mrtd (machine readable travel document). Boolean.
 *                        True if the mrtd check was performed and passed.
 *                        False if the mrtd check was performed but failed.
 * @param signature       The signature as described in the BankID Signature Profile specification. String. Base64-encoded. XML signature.
 * @param ocspResponse    The OCSP response. String. Base64-encoded.
 *                        The OCSP response is signed by a certificate that has the same issuer as the certificate being verified.
 *                        The OSCP response has an extension for Nonce. The nonce is calculated as:
 *                        SHA-1 hash over the base 64 XML signature encoded as UTF-8.
 *                        12 random bytes is added after the hash.
 *                        The nonce is 32 bytes (20 + 12).
 */
public record CompletionData(
    User user,
    Device device,
    LocalDate bankIdIssueDate,
    boolean stepUp,
    String signature,
    String ocspResponse) {
}
