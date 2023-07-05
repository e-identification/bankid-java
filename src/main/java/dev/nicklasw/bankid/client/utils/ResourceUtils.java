package dev.nicklasw.bankid.client.utils;

import dev.nicklasw.bankid.exceptions.BankIdException;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Optional;

@UtilityClass
public class ResourceUtils {

    /**
     * Tries to load a resource by name and returns a {@link InputStream}.
     *
     * @param resourceName the resource name to be loaded
     * @throws BankIdException in case of an error
     */
    public static InputStream tryInputStreamFrom(@NonNull final String resourceName) {
        @Nullable
        final InputStream resourceAsStream = classLoader().getResourceAsStream(resourceName);

        if (resourceAsStream == null) {
            throw new BankIdException(
                String.format("The resourceAsStream could not found and/or accessed. %s", resourceName));
        }

        return resourceAsStream;
    }

    /**
     * Loads a resource by name. Returns a {@link Optional#empty} is case of an error.
     *
     * @param resourceName the resource name to be loaded
     */
    public static Optional<InputStream> optionalUriFrom(@NonNull final String resourceName) {
        @Nullable
        final InputStream resourceAsStream = classLoader().getResourceAsStream(resourceName);

        return Optional.ofNullable(resourceAsStream);
    }

    private static ClassLoader classLoader() {
        return ClassLoader.getSystemClassLoader();
    }

}
