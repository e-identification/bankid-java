package dev.eidentification.bankid.configuration;

import dev.eidentification.bankid.internal.Precondition;
import org.jspecify.annotations.Nullable;

import java.io.InputStream;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * This record encapsulates the configuration required for a BankId client.
 * <p>
 * Represents essential information for interacting with the BankId system. The primary components of
 * this configuration are the base URL, PKCS12 authentication details, and the requisite certificate.
 * The base URL directs the client to the BankId system. PKCS12 information consists of the necessary
 * authentication credentials. The required certificate ensures secure access to the BankId system.
 * <p>
 * Provides two static URLs for interacting with the production and test environments of the BankId system:
 * URL_PRODUCTION: URL for the production environment,
 * URL_TEST: URL for the test environment.
 *
 * @param baseURL     the base url to either the test or production environment
 * @param pkcs12      the pkcs12 context required for authentication
 * @param certificate the certificate in form of an input stream
 */
public record Configuration(String baseURL, Pkcs12 pkcs12, InputStream certificate) {
    public static final String URL_PRODUCTION = "https://appapi2.bankid.com/rp/v6.0/";
    public static final String URL_TEST = "https://appapi2.test.bankid.com/rp/v6.0/";

    public Configuration {
        Precondition.nonNull(baseURL, () -> "baseURL cannot be null");
        Precondition.nonNull(pkcs12, () -> "PKCS12 cannot be null");
        Precondition.nonNull(certificate, () -> "Certificate cannot be null");
    }

    /**
     * Creates a new Configuration object with the provided base URL, PKCS12 information, and certificate.
     *
     * @param baseURL     the base URL used to interact with the BankID system
     * @param pkcs12      the PKCS12 information required for authentication
     * @param certificate the certificate required for authentication
     * @return a new Configuration object
     */
    public static Configuration of(final String baseURL, final Pkcs12 pkcs12, final InputStream certificate) {
        return new Configuration(baseURL, pkcs12, certificate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Configuration.class.getSimpleName() + "[", "]")
            .add("baseURL='" + baseURL + "'")
            .add("pkcs12=" + pkcs12)
            .add("certificate=" + certificate)
            .toString();
    }

    public static ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }

    public static class ConfigurationBuilder {
        @Nullable
        private String baseURL;
        @Nullable
        private Pkcs12 pkcs12;
        @Nullable
        private InputStream certificate;

        /**
         * Sets the base url for the ConfigurationBuilder.
         *
         * @param baseURL the non-null base url.
         * @return the ConfigurationBuilder instance.
         * @throws NullPointerException if baseURL is null.
         */
        public ConfigurationBuilder baseURL(final String baseURL) {
            this.baseURL = Objects.requireNonNull(baseURL);
            return this;
        }

        /**
         * Sets the personal number for the ConfigurationBuilder.
         *
         * @param pkcs12 the non-null pkcs12.
         * @return the ConfigurationBuilder instance.
         * @throws NullPointerException if pkcs12 is null.
         */
        public ConfigurationBuilder pkcs12(final Pkcs12 pkcs12) {
            this.pkcs12 = Objects.requireNonNull(pkcs12);
            return this;
        }

        /**
         * Sets the personal number for the ConfigurationBuilder.
         *
         * @param certificate the non-null certificate.
         * @return the ConfigurationBuilder instance.
         * @throws NullPointerException if certificate is null.
         */
        public ConfigurationBuilder certificate(final InputStream certificate) {
            this.certificate = Objects.requireNonNull(certificate);
            return this;
        }

        /**
         * Builds a {@link Configuration}.
         *
         * @return a PhoneSignRequest object.
         * @throws NullPointerException if {@code baseURL}, {@code pkcs12} or {@code certificate} is {@code null}
         */
        @SuppressWarnings("NullAway")
        public Configuration build() {
            //noinspection DataFlowIssue
            return new Configuration(baseURL, pkcs12, certificate);
        }

    }
}
