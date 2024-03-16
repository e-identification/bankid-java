package edu.umd.cs.findbugs.annotations;

import dev.nicklasw.bankid.internal.annotations.Internal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The SuppressFBWarnings annotation located at com.github.spotbugs.annotations isn't compatible with JPMS and Gradle
 */
@Internal
@Retention(RetentionPolicy.CLASS)
public @interface SuppressFBWarnings {
    /**
     * @see "https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html"
     */
    String[] value() default {};

    /**
     * Reason why the warning is suppressed. Use a SpotBugs issue id where appropriate.
     */
    String justification() default "";
}