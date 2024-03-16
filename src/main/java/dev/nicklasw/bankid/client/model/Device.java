package dev.nicklasw.bankid.client.model;

/**
 * Information related to the device.
 *
 * @param ipAddress The IP address of the user agent as the BankID server discovers it.
 * @param uhi       Unique hardware identifier for the user’s device.
 */
public record Device(String ipAddress, String uhi) {
}
