package dev.nicklasw.bankid.client.utils;

import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@UtilityClass
public class ResourceUtils {

    public static Optional<URI> optionalUriFrom(final String name) {
        return Optional.ofNullable(classLoader().getResource(name))
                .map(ResourceUtils::resourceToURI);
    }

    private static URI resourceToURI(final URL url) {
        try {
            return url.toURI();
        } catch (final URISyntaxException e) {
            // no op
        }

        return null;
    }

    private static ClassLoader classLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
