package dev.nicklasw.bankid.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.nicklasw.bankid.client.model.enums.CardReader;
import org.jspecify.annotations.Nullable;

/**
 * RP may use the requirement parameter to describe how a signature must be created and verified.
 * A typical use case is to require Mobile BankID or a certain card reader.
 * A requirement can be set for both auth and sign orders.
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
 * @param personalNumber      The personal number of the user. String 12 digits. Century must be included.
 *                            If the personal number is excluded, the client must be started with the autoStartToken returned in the response.
 */
public record Requirement(
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean pinCode,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    CardReader cardReader,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String certificatePolicies,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String personalNumber) {

    /**
     * Returns a new instance of RequirementBuilder.
     *
     * @return The RequirementBuilder object.
     */
    public static RequirementBuilder builder() {
        return new RequirementBuilder();
    }

    /**
     * A builder class for creating Requirement objects.
     */
    public static class RequirementBuilder {
        @Nullable
        private Boolean pinCode;
        @Nullable
        private CardReader cardReader;
        @Nullable
        private String certificatePolicies;
        @Nullable
        private String personalNumber;

        RequirementBuilder() {
        }

        /**
         * Sets the pinCode requirement for the signature.
         *
         * @param pinCode Boolean value indicating whether users are required to sign the transaction with their PIN code.
         *                If set to true, users must provide their PIN code even if they have biometrics activated.
         *                If set to false, users can sign the transaction with biometrics if available.
         * @return The RequirementBuilder object to allow method chaining.
         */
        public RequirementBuilder pinCode(@Nullable final Boolean pinCode) {
            this.pinCode = pinCode;
            return this;
        }

        /**
         * Sets the card reader requirement for the signature.
         *
         * @param cardReader The card reader requirement for the signature. The valid values are "class1" and "class2".
         * @return The RequirementBuilder object to allow method chaining.
         */
        public RequirementBuilder cardReader(@Nullable final CardReader cardReader) {
            this.cardReader = cardReader;
            return this;
        }

        /**
         * Sets the certificate policies requirement for the signature.
         *
         * @param certificatePolicies The oid in certificate policies in the user certificate. List of String.
         *                            One wildcard "" is allowed from position 5 and forward i.e. 1.2.752.78.
         * @return The RequirementBuilder object to allow method chaining.
         */
        public RequirementBuilder certificatePolicies(@Nullable final String certificatePolicies) {
            this.certificatePolicies = certificatePolicies;
            return this;
        }

        /**
         * Sets the personal number requirement for the signature.
         *
         * @param personalNumber The personal number of the user. Should be a string of 12 digits, including the century.
         *                       If the personal number is excluded, the client must be started with the autoStartToken returned in the response.
         * @return The RequirementBuilder object to allow method chaining.
         */
        public RequirementBuilder personalNumber(@Nullable final String personalNumber) {
            this.personalNumber = personalNumber;
            return this;
        }

        /**
         * Builds a Requirement object based on the set parameters.
         *
         * @return The built Requirement object.
         */
        public Requirement build() {
            return new Requirement(this.pinCode, this.cardReader, this.certificatePolicies, this.personalNumber);
        }

        @Override
        public String toString() {
            return "Requirement.RequirementBuilder(pinCode=" + this.pinCode + ", cardReader=" + this.cardReader + ", certificatePolicies=" +
                this.certificatePolicies + ", personalNumber=" + this.personalNumber + ")";
        }
    }
}
