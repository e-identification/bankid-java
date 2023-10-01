package dev.nicklasw.bankid.internal.annotations;

import dev.mccue.jsr305.Nonnull;
import dev.mccue.jsr305.meta.TypeQualifierDefault;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A common annotation to declare that parameters and return values
 * are to be considered as non-nullable by default for a given package.
 *
 * <p>Leverages JSR-305 meta-annotations to indicate nullability in Java to common
 * tools with JSR-305 support and used by Kotlin to infer nullability of Spring API.
 *
 * <p>Should be used at package level in association with {@link Nullable}
 * annotations at parameter and return value level.
 *
 * @see NonNullFields
 * @see Nullable
 * @see NonNull
 */
@Internal
@Nonnull
@TypeQualifierDefault({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNullApi {
}
