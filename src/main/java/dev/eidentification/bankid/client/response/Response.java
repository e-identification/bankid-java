package dev.eidentification.bankid.client.response;

public sealed interface Response
    permits CancelResponse, CollectResponse, ErrorResponse, OrderResponse, PhoneOrderResponse {
}
