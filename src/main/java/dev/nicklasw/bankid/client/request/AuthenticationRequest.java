package dev.nicklasw.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.nicklasw.bankid.client.model.Requirement;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class AuthenticationRequest implements Request {

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
     * Requirements on how the auth or sign order must be performed.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Requirement requirement;

    @Override
    public String getUri() {
        return "auth";
    }
}
