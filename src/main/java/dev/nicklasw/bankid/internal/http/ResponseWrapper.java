package dev.nicklasw.bankid.internal.http;

import dev.nicklasw.bankid.internal.annotations.Internal;

@Internal
@FunctionalInterface
public interface ResponseWrapper<R> {
    R unwrap();
}