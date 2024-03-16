package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.nicklasw.bankid.client.model.enums.CardReader;
import org.jspecify.annotations.Nullable;

/**
 * RP may use the requirement parameter to describe how a signature must be created and verified.
 * A typical use case is to require Mobile BankID or a certain card reader.
 * A requirement can be set for both auth and sign orders.
 *
 * <p>
 * The class also provides a static method for creating instances of `PhoneRequirementBuilder`,
 * which implements the Builder design pattern for easy creation of `PhoneRequirement` instances.
 * <p>
 * General usage is as follows:
 * {@code
 * PhoneRequirement phoneRequirement = PhoneRequirement.builder()
 * .pinCode(true)
 * .cardReader(cardReaderInstance)
 * .certificatePolicies(certificatePoliciesList)
 * .build();
 * }
 *
 * @param pinCode             Users are required to sign the transaction with their PIN code, even if they have biometrics activated.
 * @param cardReader          "class1" (default) – The transaction must be performed using a card reader where the PIN code is entered
 *                            on a computer keyboard, or a card reader of higher class.
 *                            <p>
 *                            "class2" – The transaction must be performed using a card reader where the PIN code is entered
 *                            on the reader, or a reader of higher class.
 *                            <p>
 *                            "no value" – defaults to "class1". This condition should be combined with a certificatePolicies
 *                            for a smart card to avoid undefined behaviour.
 * @param certificatePolicies The oid in certificate policies in the user certificate. List of String.
 *                            One wildcard "" is allowed from position 5 and forward i.e. 1.2.752.78.
 */
public record PhoneRequirement(
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean pinCode,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    CardReader cardReader,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String certificatePolicies) {

    /**
     * Creates a new instance of PhoneRequirementBuilder.
     *
     * @return a new instance of PhoneRequirementBuilder
     */
    public static PhoneRequirementBuilder builder() {
        return new PhoneRequirementBuilder();
    }

    /**
     * This class represents a builder for creating PhoneRequirement objects.
     * It allows setting the pin code, card reader, and certificate policies for the PhoneRequirement.
     */
    public static class PhoneRequirementBuilder {
        @Nullable
        private Boolean pinCode;
        @Nullable
        private CardReader cardReader;
        @Nullable
        private String certificatePolicies;

        PhoneRequirementBuilder() {
        }

        /**
         * Set the pinCode parameter for the PhoneRequirement record.
         *
         * @param pinCode Boolean value that indicates if users are required to sign the transaction with their PIN code.
         * @return This builder, but with pinCode property set.
         */
        public PhoneRequirementBuilder pinCode(@Nullable final Boolean pinCode) {
            this.pinCode = pinCode;
            return this;
        }

        /**
         * Set the cardReader parameter for the PhoneRequirement record.
         *
         * @param cardReader Instance of the CardReader enum that indicates the requirement for the type of card reader.
         * @return This builder, but with cardReader property set.
         */
        public PhoneRequirementBuilder cardReader(@Nullable final CardReader cardReader) {
            this.cardReader = cardReader;
            return this;
        }

        /**
         * Set the certificatePolicies parameter for the PhoneRequirement record.
         *
         * @param certificatePolicies String representing the specific certificate policies for the user certificate.
         * @return This builder, but with certificatePolicies property set.
         */
        public PhoneRequirementBuilder certificatePolicies(@Nullable final String certificatePolicies) {
            this.certificatePolicies = certificatePolicies;
            return this;
        }

        /**
         * Creates the PhoneRequirement record using the current state of the builder.
         *
         * @return A new instance of PhoneRequirement.
         */
        public PhoneRequirement build() {
            return new PhoneRequirement(this.pinCode, this.cardReader, this.certificatePolicies);
        }

        @Override
        public String toString() {
            return "PhoneRequirement.PhoneRequirementBuilder(pinCode=" + this.pinCode + ", cardReader=" + this.cardReader + ", certificatePolicies=" +
                this.certificatePolicies + ")";
        }
    }
}