package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

/**
 * RP may use the requirement parameter to describe how a signature must be created and verified.
 * A typical use case is to require Mobile BankID or a certain card reader.
 * A requirement can be set for both auth and sign orders.
 */
@Value
@Builder
public class PhoneRequirement {

    /**
     * Users are required to sign the transaction with their PIN code, even if they have biometrics activated.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean pinCode;

    /**
     * "class1" (default) – The transaction must be performed using a card reader where the PIN code is entered
     * on a computer keyboard, or a card reader of higher class.
     * <p>
     * "class2" – The transaction must be performed using a card reader where the PIN code is entered
     * on the reader, or a reader of higher class.
     * <p>
     * "<"no value">" – defaults to "class1". This condition should be combined with a certificatePolicies
     * for a smart card to avoid undefined behaviour.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    CardReader cardReader;

    /**
     * The oid in certificate policies in the user certificate. List of String.
     * One wildcard "" is allowed from position 5 and forward i.e. 1.2.752.78.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String certificatePolicies;

}
