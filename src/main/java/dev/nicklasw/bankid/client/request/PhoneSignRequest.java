package dev.nicklasw.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.nicklasw.bankid.client.model.PhoneRequirement;
import dev.nicklasw.bankid.client.model.UserNonVisibleData;
import dev.nicklasw.bankid.client.model.UserVisibleData;
import dev.nicklasw.bankid.client.model.enums.CallInitiator;
import dev.nicklasw.bankid.client.model.enums.VisibleDataFormat;
import dev.nicklasw.bankid.internal.Precondition;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Request to initiate a sign order when the user is talking to the RP over the phone.
 *
 * @param personalNumber        The personal number of the user. String 12 digits. Century must be included.
 *                              If the personal number is excluded, the client must be started with the autoStartToken returned in the response.
 * @param callInitiator         Indicate if the user or the RP initiated the phone call.
 * @param requirement           Requirements on how the auth or sign order must be performed.
 * @param userVisibleData       The text to be displayed and signed.
 *                              The text can be formatted using CR, LF and CRLF for new lines. 1--40 000 characters after base 64 encoding.
 * @param userNonVisibleData    Data not displayed for the user. 1-200 000 characters after base 64-encoding.
 * @param userVisibleDataFormat This parameter indicates that userVisibleData holds formatting characters which,
 *                              will potentially make the text displayed to the user nicer to look at.
 */
public record PhoneSignRequest(
    String personalNumber,

    CallInitiator callInitiator,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    PhoneRequirement requirement,

    UserVisibleData userVisibleData,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserNonVisibleData userNonVisibleData,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    VisibleDataFormat userVisibleDataFormat) implements Request {

    /**
     * PhoneSignRequest constructor.
     *
     * @throws NullPointerException if {@code personalNumber}, {@code callInitiator} or {@code userVisibleData} is {@code null}
     */
    public PhoneSignRequest {
        Precondition.nonNull(personalNumber, () -> "Person number cannot be null");
        Precondition.nonNull(callInitiator, () -> "Call initiator cannot be null");
        Precondition.nonNull(userVisibleData, () -> "User visible data cannot be null");
    }

    public static PhoneSignRequestBuilder builder() {
        return new PhoneSignRequestBuilder();
    }

    @Override
    public String getUri() {
        return "phone/sign";
    }

    public static class PhoneSignRequestBuilder {
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

        private PhoneSignRequestBuilder() {
        }

        /**
         * Sets the personal number for the PhoneSignRequestBuilder.
         *
         * @param personalNumber the non-null personal number.
         * @return the PhoneSignRequestBuilder instance.
         * @throws NullPointerException if personalNumber is null.
         */
        public PhoneSignRequestBuilder personalNumber(final String personalNumber) {
            this.personalNumber = Objects.requireNonNull(personalNumber);
            return this;
        }

        /**
         * Sets the call initiator for the PhoneSignRequestBuilder.
         *
         * @param callInitiator the non-null call initiator enum value.
         * @return the PhoneSignRequestBuilder instance.
         * @throws NullPointerException if callInitiator is null.
         */
        public PhoneSignRequestBuilder callInitiator(final CallInitiator callInitiator) {
            this.callInitiator = Objects.requireNonNull(callInitiator);
            return this;
        }

        /**
         * Sets the requirement for the PhoneSignRequestBuilder.
         *
         * @param requirement the nullable PhoneRequirement indicating the requirement for creating and verifying signatures.
         * @return the PhoneSignRequestBuilder instance.
         */
        public PhoneSignRequestBuilder requirement(@Nullable final PhoneRequirement requirement) {
            this.requirement = requirement;
            return this;
        }

        /**
         * Sets the user visible data for the PhoneSignRequestBuilder.
         *
         * @param userVisibleData the non-null user visible data.
         * @return the PhoneSignRequestBuilder instance.
         * @throws NullPointerException if userVisibleData is null.
         */
        public PhoneSignRequestBuilder userVisibleData(final UserVisibleData userVisibleData) {
            this.userVisibleData = Objects.requireNonNull(userVisibleData);
            return this;
        }

        /**
         * Sets the user's non-visible data for the PhoneSignRequestBuilder.
         * Non-visible data is optional information that is not displayed to the user during the authentication process.
         *
         * @param userNonVisibleData the non-null user's non-visible data.
         * @return the PhoneSignRequestBuilder instance.
         */
        public PhoneSignRequestBuilder userNonVisibleData(@Nullable final UserNonVisibleData userNonVisibleData) {
            this.userNonVisibleData = userNonVisibleData;
            return this;
        }

        /**
         * Sets the user visible data format for the PhoneSignRequestBuilder.
         *
         * @param userVisibleDataFormat the nullable VisibleDataFormat indicating the format of the user visible data.
         * @return the PhoneSignRequestBuilder instance.
         */
        public PhoneSignRequestBuilder userVisibleDataFormat(@Nullable final VisibleDataFormat userVisibleDataFormat) {
            this.userVisibleDataFormat = userVisibleDataFormat;
            return this;
        }

        /**
         * Builds a {@link PhoneSignRequest}.
         *
         * @return a PhoneSignRequest object.
         * @throws NullPointerException if {@code personalNumber}, {@code callInitiator} or {@code userVisibleData} is {@code null}
         */
        @SuppressWarnings("NullAway")
        public PhoneSignRequest build() {
            //noinspection DataFlowIssue
            return new PhoneSignRequest(
                this.personalNumber,
                this.callInitiator,
                this.requirement,
                this.userVisibleData,
                this.userNonVisibleData,
                this.userVisibleDataFormat);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", PhoneSignRequestBuilder.class.getSimpleName() + "[", "]")
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
