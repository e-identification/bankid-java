package dev.nicklasw.bankid.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class Cert {
    private final String notBefore;
    private final String notAfter;
}
