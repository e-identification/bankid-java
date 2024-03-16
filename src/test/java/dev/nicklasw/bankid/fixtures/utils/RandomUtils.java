package dev.nicklasw.bankid.fixtures.utils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Random;

public final class RandomUtils {

    private static final Random RANDOM = new Random();

    private RandomUtils() {

    }

    @SuppressFBWarnings(value = "DMI_RANDOM_USED_ONLY_ONCE", justification = "Should be fine to create and use only once")
    public static int between(final int lowerBound, final int higherBound) {
        return RANDOM.nextInt(higherBound - lowerBound) + lowerBound;
    }

}
