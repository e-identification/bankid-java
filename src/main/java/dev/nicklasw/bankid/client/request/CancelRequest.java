package dev.nicklasw.bankid.client.request;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CancelRequest implements Request {

    /**
     * The orderRef retrieved from a order response.
     */
    @NonNull
    String orderRef;

    @Override
    public String getUri() {
        return "cancel";
    }
}
