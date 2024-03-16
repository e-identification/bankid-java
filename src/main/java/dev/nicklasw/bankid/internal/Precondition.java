package dev.nicklasw.bankid.internal;

import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

public final class Precondition {

    private Precondition() {
    }

    /**
     * Checks if the provided subject is not null. If it is null, a NullPointerException is thrown with the message
     * provided by the messageSupplier.
     *
     * @param <T>             the type of the subject
     * @param subject         the subject to check for null
     * @param messageSupplier a supplier for the exception message
     * @throws NullPointerException if the subject is null
     */
    public static <T> void nonNull(@Nullable final T subject, final Supplier<String> messageSupplier) {
        if (subject == null) {
            throw new NullPointerException(messageSupplier.get());
        }
    }

}
