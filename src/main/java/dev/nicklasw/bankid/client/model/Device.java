package dev.nicklasw.bankid.client.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Device {
    /**
     * The IP address of the user agent as the BankID server discovers it.
     */
    String ipAddress;

    /**
     * Unique hardware identifier for the user’s device.
     */
    String uhi;
}
