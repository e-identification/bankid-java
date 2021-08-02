package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Requirement {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String cardReader;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String certificatePolicies;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String issuerCn;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Boolean autoStartTokenRequired;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Boolean allowFingerprint;
}
