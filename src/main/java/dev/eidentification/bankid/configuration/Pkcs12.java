package dev.eidentification.bankid.configuration;

import dev.eidentification.bankid.internal.Precondition;

import java.io.InputStream;

/**
 * The Pkcs12 class represents a PKCS12 information required for authentication.
 *
 * @param inputStream the certificate in form of an input stream
 * @param password    the password for the pkcs12 file if any
 */
public record Pkcs12(InputStream inputStream, String password) {

    public Pkcs12 {
        Precondition.nonNull(inputStream, () -> "Path cannot be null");
        Precondition.nonNull(password, () -> "Password cannot be null");
    }

    public static Pkcs12 of(final InputStream inputStream, final String password) {
        return new Pkcs12(inputStream, password);
    }

}
