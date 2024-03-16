package dev.eidentification.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.eidentification.bankid.client.model.Requirement;
import dev.eidentification.bankid.client.model.UserNonVisibleData;
import dev.eidentification.bankid.client.model.UserVisibleData;
import dev.eidentification.bankid.client.model.enums.VisibleDataFormat;
import dev.eidentification.bankid.internal.Precondition;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Request to initiates a signing order.
 *
 * @param endUserIp             The user IP address as seen by RP. String, IPv4 and IPv6 is allowed.
 * @param requirement           Requirements on how the auth or sign order must be performed.
 * @param userVisibleData       The text to be displayed and signed. The text can be formatted using CR, LF and CRLF for new lines.
 *                              1--40 000 characters after base 64 encoding.
 * @param userNonVisibleData    Data not displayed for the user. 1-200 000 characters after base 64-encoding.
 * @param userVisibleDataFormat This parameter indicates that userVisibleData holds formatting characters which, will potentially
 *                              make the text displayed to the user nicer to look at.
 */
public record SignRequest(
    String endUserIp,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Requirement requirement,

    UserVisibleData userVisibleData,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserNonVisibleData userNonVisibleData,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    VisibleDataFormat userVisibleDataFormat) implements Request {

    /**
     * SignRequest constructor.
     *
     * @throws NullPointerException if {@code endUserIp} or {@code userVisibleData} is {@code null}
     */
    public SignRequest {
        Precondition.nonNull(endUserIp, () -> "EndUserIp cannot be null");
        Precondition.nonNull(userVisibleData, () -> "User visible data cannot be null");
    }

    public static SignRequestBuilder builder() {
        return new SignRequestBuilder();
    }

    @Override
    public String getUri() {
        return "sign";
    }

    public static class SignRequestBuilder {
        @Nullable
        private String endUserIp;
        @Nullable
        private Requirement requirement;
        @Nullable
        private UserVisibleData userVisibleData;
        @Nullable
        private UserNonVisibleData userNonVisibleData;
        @Nullable
        private VisibleDataFormat userVisibleDataFormat;

        private SignRequestBuilder() {
        }

        /**
         * Sets the end user IP address for the SignRequest.
         *
         * @param endUserIp The user IP address as seen by the RP. String, IPv4 and IPv6 is allowed.
         * @return The updated SignRequestBuilder object.
         */
        public SignRequestBuilder endUserIp(final String endUserIp) {
            this.endUserIp = Objects.requireNonNull(endUserIp);
            return this;
        }

        /**
         * Sets the requirement for the SignRequest.
         *
         * @param requirement The requirement for the SignRequest.
         * @return The updated SignRequestBuilder object.
         */
        public SignRequestBuilder requirement(@Nullable final Requirement requirement) {
            this.requirement = requirement;
            return this;
        }

        /**
         * Sets the user visible data for the SignRequest.
         *
         * @param userVisibleData The {@link UserVisibleData} object containing the text to be displayed and signed.
         * @return The updated {@link SignRequestBuilder} object.
         */
        public SignRequestBuilder userVisibleData(final UserVisibleData userVisibleData) {
            this.userVisibleData = Objects.requireNonNull(userVisibleData);
            return this;
        }

        /**
         * Sets the user non-visible data for the SignRequest.
         *
         * @param userNonVisibleData The user non-visible data to be included in the SignRequest. Can be null.
         * @return The updated SignRequestBuilder object.
         */
        public SignRequestBuilder userNonVisibleData(@Nullable final UserNonVisibleData userNonVisibleData) {
            this.userNonVisibleData = userNonVisibleData;
            return this;
        }

        /**
         * Sets the user visible data format for the SignRequest.
         *
         * @param userVisibleDataFormat The format of the user visible data. Use the {@link VisibleDataFormat} enum to specify the format.
         *                              Can be null if no specific format is required.
         * @return The updated SignRequestBuilder object.
         */
        public SignRequestBuilder userVisibleDataFormat(@Nullable final VisibleDataFormat userVisibleDataFormat) {
            this.userVisibleDataFormat = userVisibleDataFormat;
            return this;
        }

        /**
         * Builds a {@link SignRequest}.
         *
         * @return the SignRequest object
         * @throws NullPointerException if {@code endUserIp} or {@code userVisibleData} is {@code null}
         */
        @SuppressWarnings("NullAway")
        public SignRequest build() {
            //noinspection DataFlowIssue
            return new SignRequest(
                this.endUserIp,
                this.requirement,
                this.userVisibleData,
                this.userNonVisibleData,
                this.userVisibleDataFormat);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", SignRequestBuilder.class.getSimpleName() + "[", "]")
                .add("endUserIp='" + endUserIp + "'")
                .add("requirement=" + requirement)
                .add("userVisibleData=" + userVisibleData)
                .add("userNonVisibleData=" + userNonVisibleData)
                .add("userVisibleDataFormat=" + userVisibleDataFormat)
                .toString();
        }
    }
}
