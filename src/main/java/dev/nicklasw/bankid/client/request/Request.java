package dev.nicklasw.bankid.client.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Request {
    @JsonIgnore
    String getUri();
}
