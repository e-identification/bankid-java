package dev.nicklasw.bankid.utils;

import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtils {

    private static final Random RANDOM = new Random();

    @SuppressFBWarnings(value = "DMI_RANDOM_USED_ONLY_ONCE", justification = "Should be fine to create and use only once")
    public static int between(final int lowerBound, final int higherBound) {
        return RANDOM.nextInt(higherBound - lowerBound) + lowerBound;
    }

}
