package dev.nicklasw.bankid.client.request;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CollectRequest implements Request {

    /**
     * The orderRef from the response from authentication or sign.
     */
    @NonNull
    String orderRef;

    @Override
    public String getUri() {
        return "collect";
    }
}
