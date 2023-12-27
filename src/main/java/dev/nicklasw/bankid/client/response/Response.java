package dev.nicklasw.bankid.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.nicklasw.bankid.client.model.ErrorType;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

@ToString
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class Response {
    @Nullable
    @JsonProperty("errorCode")
    protected ErrorType error;

    @Nullable
    @JsonProperty("details")
    protected String details;

    public boolean hasErrorCode() {
        return error != null;
    }

    public Optional<ErrorType> optionalErrorType() {
        return Optional.ofNullable(error);
    }

    public Optional<String> optionalDetails() {
        return Optional.ofNullable(details);
    }

}
