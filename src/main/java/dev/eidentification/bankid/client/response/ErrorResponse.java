package dev.eidentification.bankid.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.eidentification.bankid.client.model.ErrorType;

/**
 * The ErrorResponse class is a record that represents an error response from the BankID API.
 * It contains information about the error code 'error' and the details of the error 'details'.
 * Both 'error' and 'details' can be accessed using the associated getter methods.
 *
 * @param error   The 'error' field represents the type of error.
 * @param details The 'details' field holds more detailed information about the error.
 */
public record ErrorResponse(ErrorType error, String details) implements Response {

    @JsonCreator
    public ErrorResponse(@JsonProperty("errorCode") final ErrorType error, @JsonProperty("details") final String details) {
        this.error = error;
        this.details = details;
    }
}
