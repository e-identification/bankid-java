package dev.nicklasw.bankid.client.ssl;

import lombok.SneakyThrows;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class SSLContexts {

    private KeyManagerFactory keyManagerFactory;
    private TrustManagerFactory trustManagerFactory;

    public static SSLContexts builder() {
        return new SSLContexts();
    }

    @SneakyThrows
    public SSLContext build() {
        final SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

        return sslContext;
    }

    @SneakyThrows
    public SSLContexts loadKeyManager(final Path path, final String password) {
        final KeyStore clientStore = KeyStore.getInstance("PKCS12");

        try (var inputStream = Files.newInputStream(path)) {
            clientStore.load(inputStream, password.toCharArray());
        } catch (final IOException e) {
            throw e;
        }

        keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(clientStore, password.toCharArray());

        return this;
    }

    @SneakyThrows
    public SSLContexts loadTrustManager(final Path path) {
        final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        final X509Certificate caCert = (X509Certificate) certificateFactory.generateCertificate(Files.newInputStream(path));

        trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());

        final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        keyStore.setCertificateEntry("caCert", caCert);

        trustManagerFactory.init(keyStore);

        return this;
    }

}
