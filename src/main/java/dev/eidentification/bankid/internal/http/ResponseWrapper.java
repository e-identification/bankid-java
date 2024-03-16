package dev.eidentification.bankid.internal.http;

import dev.eidentification.bankid.internal.annotations.Internal;

@Internal
@FunctionalInterface
public interface ResponseWrapper<R> {
    R unwrap();
}