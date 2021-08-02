package dev.nicklasw.bankid.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class Device {
    private final String ipAddress;
}
