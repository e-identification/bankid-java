package dev.nicklasw.bankid.internal.annotations;

import dev.mccue.jsr305.Nonnull;
import dev.mccue.jsr305.meta.TypeQualifierDefault;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A common annotation to declare that fields are to be considered as non-nullable by
 * default for a given package.
 * <p>
 * Leverages JSR-305 meta-annotations to indicate nullability in Java to common tools with
 * JSR-305 support and used by Kotlin to infer nullability of the API.
 * <p>
 * Should be used at package level in association with {@link Nullable} annotations at
 * field level.
 * <p>
 * NOTE: This file has been copied from {@code org.springframework.lang}.
 *
 * @see NonNullFields
 * @see Nullable
 * @see NonNull
 */
@Internal
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Nonnull
@TypeQualifierDefault(ElementType.FIELD)
public @interface NonNullFields {

}

