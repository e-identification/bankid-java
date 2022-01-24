package dev.nicklasw.bankid.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

import dev.nicklasw.bankid.exceptions.BankIdException;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResourceUtils {

    /**
     * Tries to load a resource by name and returns a {@link URI}.
     *
     * @param resourceName the resource name to be loaded
     * @throws BankIdException in case of an error
     */
    public static URI tryUriFrom(@NonNull final String resourceName) {
        final URL resource = classLoader().getResource(resourceName);

        if (resource == null) {
            throw new BankIdException(
                String.format("The resource could not found and/or accessed. %s", resourceName));
        }

        return tryResourceToURI(resource);
    }

    /**
     * Loads a resource by name. Returns a {@link Optional#empty} is case of an error.
     *
     * @param resourceName the resource name to be loaded
     * @throws BankIdException in case of an error
     */
    public static Optional<URI> optionalUriFrom(@NonNull final String resourceName) {
        return Optional.ofNullable(classLoader().getResource(resourceName))
            .map(ResourceUtils::resourceToURI);
    }

    @Nullable
    private static URI resourceToURI(final URL url) {
        try {
            return url.toURI();
        } catch (final URISyntaxException e) {
            // no op
        }

        return null;
    }

    private static URI tryResourceToURI(final URL url) {
        try {
            return url.toURI();
        } catch (final URISyntaxException e) {
            throw new BankIdException("Invalid resource provided", e);
        }
    }

    private static ClassLoader classLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
