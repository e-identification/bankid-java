package dev.nicklasw.bankid.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class CompletionData {
    private final User user;
    private final Device device;
    private final Cert cert;
    private final String signature;
    private final String ocspResponse;
}
