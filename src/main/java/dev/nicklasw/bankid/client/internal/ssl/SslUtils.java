package dev.nicklasw.bankid.client.internal.ssl;

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

@UtilityClass
public class SslUtils {

    @SneakyThrows
    public SSLContext tryCreateSSLContext(final KeyManagerFactory keyManagerFactory, final TrustManagerFactory trustManagerFactory) {
        final SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

        return sslContext;
    }

    @SneakyThrows
    public static KeyManagerFactory tryCreateKeyManager(@NonNull final InputStream keystore, @NonNull final String password) {
        final KeyStore clientStore = KeyStore.getInstance("PKCS12");
        clientStore.load(keystore, password.toCharArray());

        final KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(clientStore, password.toCharArray());

        return keyManagerFactory;
    }

    @SneakyThrows
    public static TrustManagerFactory tryCreateTrustManager(@NonNull final InputStream certificate) {
        final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        final X509Certificate caCert = (X509Certificate) certificateFactory.generateCertificate(certificate);

        final TrustManagerFactory trustManagerFactory = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm());

        final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        keyStore.setCertificateEntry("caCert", caCert);

        trustManagerFactory.init(keyStore);

        return trustManagerFactory;
    }


}
