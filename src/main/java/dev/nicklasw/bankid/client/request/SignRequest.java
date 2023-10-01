package dev.nicklasw.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.nicklasw.bankid.client.model.Requirement;
import dev.nicklasw.bankid.client.model.UserNonVisibleData;
import dev.nicklasw.bankid.client.model.UserVisibleData;
import dev.nicklasw.bankid.client.model.VisibleDataFormat;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class SignRequest implements Request {

    /**
     * The user IP address as seen by RP. String, IPv4 and IPv6 is allowed.
     */
    @NonNull
    String endUserIp;

    /**
     * Requirements on how the auth or sign order must be performed.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Requirement requirement;

    /**
     * The text to be displayed and signed. The text can be formatted using CR, LF and CRLF for new lines.
     * 1--40 000 characters after base 64 encoding.
     */
    @NonNull
    UserVisibleData userVisibleData;

    /**
     * Data not displayed for the user.
     * 1-200 000 characters after base 64-encoding.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserNonVisibleData userNonVisibleData;

    /**
     * This parameter indicates that userVisibleData holds formatting characters which, will potentially
     * make the text displayed to the user nicer to look at.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    VisibleDataFormat userVisibleDataFormat;

    @Override
    public String getUri() {
        return "sign";
    }
}
