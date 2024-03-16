package dev.eidentification.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.eidentification.bankid.internal.Precondition;
import dev.eidentification.bankid.client.model.PhoneRequirement;
import dev.eidentification.bankid.client.model.UserNonVisibleData;
import dev.eidentification.bankid.client.model.UserVisibleData;
import dev.eidentification.bankid.client.model.enums.CallInitiator;
import dev.eidentification.bankid.client.model.enums.VisibleDataFormat;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Request to initiates an authentication order when the user is talking to the RP over the phone.
 *
 * @param personalNumber        The personal number of the user. String 12 digits. Century must be included. If the personal number is excluded,
 *                              the client must be started with the autoStartToken returned in the response.
 * @param callInitiator         Indicate if the user or the RP initiated the phone call.
 * @param requirement           Requirements on how the auth or sign order must be performed.
 * @param userVisibleData       The text to be displayed and signed. The text can be formatted using CR, LF and CRLF for new lines. 1--40 000 characters
 *                              after base 64 encoding.
 * @param userNonVisibleData    Data not displayed for the user. 1-200 000 characters after base 64-encoding.
 * @param userVisibleDataFormat This parameter indicates that userVisibleData holds formatting characters which,
 *                              will potentially make the text displayed to the user nicer to look at.
 */
public record PhoneAuthenticationRequest(
    String personalNumber,

    CallInitiator callInitiator,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    PhoneRequirement requirement,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserVisibleData userVisibleData,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserNonVisibleData userNonVisibleData,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    VisibleDataFormat userVisibleDataFormat) implements Request {

    /**
     * PhoneAuthenticationRequest constructor.
     *
     * @throws NullPointerException if {@code personalNumber} or {@code callInitiator} is {@code null}
     */
    public PhoneAuthenticationRequest {
        Precondition.nonNull(personalNumber, () -> "Person number cannot be null");
        Precondition.nonNull(callInitiator, () -> "Call initiator cannot be null");
    }

    public static PhoneAuthenticationRequestBuilder builder() {
        return new PhoneAuthenticationRequestBuilder();
    }

    @Override
    public String getUri() {
        return "phone/auth";
    }

    /**
     * PhoneAuthenticationRequestBuilder is a builder class for creating instances of PhoneAuthenticationRequest.
     */
    public static class PhoneAuthenticationRequestBuilder {
        @Nullable
        private String personalNumber;
        @Nullable
        private CallInitiator callInitiator;
        @Nullable
        private PhoneRequirement requirement;
        @Nullable
        private UserVisibleData userVisibleData;
        @Nullable
        private UserNonVisibleData userNonVisibleData;
        @Nullable
        private VisibleDataFormat userVisibleDataFormat;

        private PhoneAuthenticationRequestBuilder() {
        }

        /**
         * Sets the personal number for the PhoneAuthenticationRequestBuilder.
         *
         * @param personalNumber the non-null personal number of the user. String 12 digits. Century must be included. If the personal number is excluded,
         *                       the client must be started with the autoStartToken returned in the response.
         * @return the updated PhoneAuthenticationRequestBuilder object.
         * @throws NullPointerException if personalNumber is null
         */
        public PhoneAuthenticationRequestBuilder personalNumber(final String personalNumber) {
            this.personalNumber = Objects.requireNonNull(personalNumber);
            return this;
        }

        /**
         * Sets the call initiator for the PhoneAuthenticationRequestBuilder.
         *
         * @param callInitiator the non-null CallInitiator representing the initiator of the phone call.
         * @return the updated PhoneAuthenticationRequestBuilder object.
         * @throws NullPointerException if callInitiator is null
         */
        public PhoneAuthenticationRequestBuilder callInitiator(final CallInitiator callInitiator) {
            this.callInitiator = Objects.requireNonNull(callInitiator);
            return this;
        }

        /**
         * Sets the requirement for phone authentication.
         *
         * @param requirement the PhoneRequirement object representing the required conditions for authentication. Can be null.
         * @return the updated PhoneAuthenticationRequestBuilder object.
         */
        public PhoneAuthenticationRequestBuilder requirement(@Nullable final PhoneRequirement requirement) {
            this.requirement = requirement;
            return this;
        }

        /**
         * Sets the user visible data for the PhoneAuthenticationRequestBuilder.
         *
         * @param userVisibleData the nullable UserVisibleData object representing the visible data for authentication.
         * @return the updated PhoneAuthenticationRequestBuilder object.
         */
        public PhoneAuthenticationRequestBuilder userVisibleData(@Nullable final UserVisibleData userVisibleData) {
            this.userVisibleData = userVisibleData;
            return this;
        }

        /**
         * Sets the non-visible data for the user in the PhoneAuthenticationRequestBuilder.
         *
         * @param userNonVisibleData the nullable UserNonVisibleData object representing the non-visible data for authentication.
         * @return the updated PhoneAuthenticationRequestBuilder object.
         * @see UserNonVisibleData
         */
        public PhoneAuthenticationRequestBuilder userNonVisibleData(@Nullable final UserNonVisibleData userNonVisibleData) {
            this.userNonVisibleData = userNonVisibleData;
            return this;
        }

        /**
         * Sets the user visible data format for the PhoneAuthenticationRequestBuilder.
         *
         * @param userVisibleDataFormat the nullable VisibleDataFormat object representing the format of visible data for authentication.
         * @return the updated PhoneAuthenticationRequestBuilder object.
         */
        public PhoneAuthenticationRequestBuilder userVisibleDataFormat(@Nullable final VisibleDataFormat userVisibleDataFormat) {
            this.userVisibleDataFormat = userVisibleDataFormat;
            return this;
        }

        /**
         * Builds a {@link PhoneAuthenticationRequest}.
         *
         * @return the PhoneAuthenticationRequest object
         * @throws NullPointerException if {@code personalNumber} or {@code callInitiator} is {@code null}
         */
        @SuppressWarnings("NullAway")
        public PhoneAuthenticationRequest build() {
            //noinspection DataFlowIssue
            return new PhoneAuthenticationRequest(
                this.personalNumber,
                this.callInitiator,
                this.requirement,
                this.userVisibleData,
                this.userNonVisibleData,
                this.userVisibleDataFormat);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", PhoneAuthenticationRequestBuilder.class.getSimpleName() + "[", "]")
                .add("personalNumber='" + personalNumber + "'")
                .add("callInitiator=" + callInitiator)
                .add("requirement=" + requirement)
                .add("userVisibleData=" + userVisibleData)
                .add("userNonVisibleData=" + userNonVisibleData)
                .add("userVisibleDataFormat=" + userVisibleDataFormat)
                .toString();
        }
    }
}
