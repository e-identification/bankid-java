package dev.nicklasw.bankid.internal.ssl;

import dev.nicklasw.bankid.internal.annotations.Internal;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Internal
@UtilityClass
public class SslUtils {

    @SneakyThrows
    public SSLContext tryCreateSSLContext(final KeyManagerFactory keyManagerFactory, final TrustManagerFactory trustManagerFactory) {
        final SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

        return sslContext;
    }

    @SneakyThrows
    public static KeyManagerFactory tryCreateKeyManager(@NonNull final InputStream keystoreInputStream, @NonNull final String password) {
        final KeyStore clientStore = KeyStore.getInstance("PKCS12");
        clientStore.load(keystoreInputStream, password.toCharArray());

        final KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(clientStore, password.toCharArray());

        return keyManagerFactory;
    }

    @SneakyThrows
    public static TrustManagerFactory tryCreateTrustManager(@NonNull final InputStream certificateInputStream) {
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
