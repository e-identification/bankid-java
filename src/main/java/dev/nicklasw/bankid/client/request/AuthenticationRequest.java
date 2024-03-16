package dev.nicklasw.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.nicklasw.bankid.client.model.Requirement;
import dev.nicklasw.bankid.client.model.UserNonVisibleData;
import dev.nicklasw.bankid.client.model.UserVisibleData;
import dev.nicklasw.bankid.client.model.enums.VisibleDataFormat;
import dev.nicklasw.bankid.internal.Precondition;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents an authentication request.
 *
 * @param endUserIp             The user IP address as seen by RP. String, IPv4 and IPv6 is allowed.
 * @param requirement           Requirements on how the auth or sign order must be performed.
 * @param userVisibleData       The text to be displayed and signed. The text can be formatted using CR, LF and CRLF for new lines.
 *                              1--40 000 characters after base 64 encoding.
 * @param userNonVisibleData    Data not displayed for the user.
 *                              1-200 000 characters after base 64-encoding.
 * @param userVisibleDataFormat This parameter indicates that userVisibleData holds formatting characters which, will potentially
 *                              make the text displayed to the user nicer to look at.
 */
public record AuthenticationRequest(
    String endUserIp,

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Requirement requirement,

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
     * AuthenticationRequest constructor.
     *
     * @throws NullPointerException if {@code endUserIp} is {@code null}
     */
    public AuthenticationRequest {
        Precondition.nonNull(endUserIp, () -> "EndUserIp cannot be null");
    }

    /**
     * Creates a new instance of AuthenticationRequestBuilder.
     *
     * @return A new instance of AuthenticationRequestBuilder.
     */
    public static AuthenticationRequestBuilder builder() {
        return new AuthenticationRequestBuilder();
    }

    /**
     * Returns the URI of the authentication request.
     *
     * @return The URI of the authentication request.
     */
    @Override
    public String getUri() {
        return "auth";
    }

    public static class AuthenticationRequestBuilder {
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

        private AuthenticationRequestBuilder() {
        }

        /**
         * Sets the end user IP address for the authentication request.
         *
         * @param endUserIp The user IP address as seen by RP.
         * @return This AuthenticationRequestBuilder instance.
         * @throws NullPointerException if endUserIp is null.
         */
        public AuthenticationRequestBuilder endUserIp(final String endUserIp) {
            this.endUserIp = Objects.requireNonNull(endUserIp);
            return this;
        }

        /**
         * Sets the requirement for the authentication request.
         *
         * @param requirement The requirement object describing how the signature must be created and verified.
         *                    This parameter can be null if no specific requirement is needed.
         * @return This AuthenticationRequestBuilder instance.
         */
        public AuthenticationRequestBuilder requirement(@Nullable final Requirement requirement) {
            this.requirement = requirement;
            return this;
        }

        /**
         * Sets the user visible data for the authentication request.
         *
         * @param userVisibleData The user visible data to be set. This parameter can be null if no user visible data is needed.
         * @return This AuthenticationRequestBuilder instance.
         */
        public AuthenticationRequestBuilder userVisibleData(@Nullable final UserVisibleData userVisibleData) {
            this.userVisibleData = userVisibleData;
            return this;
        }

        /**
         * Sets the user non-visible data for the authentication request.
         *
         * @param userNonVisibleData The user non-visible data to be set. This parameter can be null if no user non-visible data is needed.
         * @return This AuthenticationRequestBuilder instance.
         */
        public AuthenticationRequestBuilder userNonVisibleData(@Nullable final UserNonVisibleData userNonVisibleData) {
            this.userNonVisibleData = userNonVisibleData;
            return this;
        }

        /**
         * Sets the user visible data format for the authentication request.
         *
         * @param userVisibleDataFormat The user visible data format to be set. This parameter can be null if no specific format is needed.
         * @return This AuthenticationRequestBuilder instance.
         */
        public AuthenticationRequestBuilder userVisibleDataFormat(@Nullable final VisibleDataFormat userVisibleDataFormat) {
            this.userVisibleDataFormat = userVisibleDataFormat;
            return this;
        }

        /**
         * Builds an AuthenticationRequest object with the provided parameters.
         *
         * @return The built AuthenticationRequest object.
         */
        @SuppressWarnings("NullAway")
        public AuthenticationRequest build() {
            //noinspection DataFlowIssue
            return new AuthenticationRequest(
                this.endUserIp,
                this.requirement,
                this.userVisibleData,
                this.userNonVisibleData,
                this.userVisibleDataFormat);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", AuthenticationRequestBuilder.class.getSimpleName() + "[", "]")
                .add("endUserIp='" + endUserIp + "'")
                .add("requirement=" + requirement)
                .add("userVisibleData=" + userVisibleData)
                .add("userNonVisibleData=" + userNonVisibleData)
                .add("userVisibleDataFormat=" + userVisibleDataFormat)
                .toString();
        }
    }
}
