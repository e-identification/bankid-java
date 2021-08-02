package dev.nicklasw.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.nicklasw.bankid.client.model.Requirement;
import dev.nicklasw.bankid.client.model.UserNonVisibleData;
import dev.nicklasw.bankid.client.model.UserVisibleData;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class SignRequest implements Request {

    /**
     * The personal number of the user. String 12 digits. Century must be included.
     * If the personal number is excluded, the client must be started with the autoStartToken returned in the response.
     */
    @NonNull
    private final String personalNumber;

    /**
     * The user IP address as seen by RP. String, IPv4 and IPv6 is allowed.
     */
    @NonNull
    private final String endUserIp;

    /**
     * The text to be displayed and signed. The text can be formatted using CR, LF and CRLF for new lines.
     * 1--40 000 characters after base 64 encoding.
     */
    @NonNull
    private final UserVisibleData userVisibleData;

    /**
     * Data not displayed for the user.
     * 1-200 000 characters after base 64-encoding.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final UserNonVisibleData userNonVisibleData;

    /**
     * Requirements on how the auth or sign order must be performed.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Requirement requirement;

    @Override
    public String getUri() {
        return "sign";
    }
}
