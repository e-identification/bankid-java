package dev.nicklasw.bankid.client.response;

@FunctionalInterface
public interface ResponseWrapper<R> {
    R unwrap();
}