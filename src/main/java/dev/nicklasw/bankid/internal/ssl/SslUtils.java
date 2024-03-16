package dev.nicklasw.bankid.internal.ssl;

import dev.nicklasw.bankid.internal.annotations.Internal;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Objects;

@Internal
public final class SslUtils {

    private SslUtils() {

    }

    public static SSLContext tryCreateSSLContext(final KeyManagerFactory keyManagerFactory, final TrustManagerFactory trustManagerFactory)
        throws GeneralSecurityException {
        final SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

        return sslContext;
    }

    public static KeyManagerFactory tryCreateKeyManager(final InputStream keystoreInputStream, final String password)
        throws GeneralSecurityException, IOException {
        Objects.requireNonNull(keystoreInputStream);
        Objects.requireNonNull(password);

        final KeyStore clientStore = KeyStore.getInstance("PKCS12");
        clientStore.load(keystoreInputStream, password.toCharArray());

        final KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(clientStore, password.toCharArray());

        return keyManagerFactory;
    }

    public static TrustManagerFactory tryCreateTrustManager(final InputStream certificateInputStream)
        throws GeneralSecurityException, IOException {
        Objects.requireNonNull(certificateInputStream);

        final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        final X509Certificate caCert = (X509Certificate) certificateFactory.generateCertificate(certificateInputStream);

        final TrustManagerFactory trustManagerFactory = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm());

        final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        keyStore.setCertificateEntry("caCert", caCert);

        trustManagerFactory.init(keyStore);

        return trustManagerFactory;
    }

}
