package dev.nicklasw.bankid.client.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class CancelRequest implements Request {

    /**
     * The orderRef from the response from authentication or sign.
     */
    @NonNull
    private final String orderRef;

    @Override
    public String getUri() {
        return "cancel";
    }
}
