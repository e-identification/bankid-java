package dev.nicklasw.bankid.internal.annotations;

import dev.mccue.jsr305.Nonnull;
import dev.mccue.jsr305.meta.TypeQualifierNickname;
import dev.mccue.jsr305.meta.When;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A common annotation to declare that annotated elements can be {@code null} under some
 * circumstance. Leverages JSR 305 meta-annotations to indicate nullability in Java to
 * common tools with JSR 305 support and used by Kotlin to infer nullability of the API.
 * <p>
 * Should be used at parameter, return value, and field level. Methods override should
 * repeat parent {@code @Nullable} annotations unless they behave differently.
 * <p>
 * Can be used in association with {@code NonNullApi} or {@code @NonNullFields} to
 * override the default non-nullable semantic to nullable.
 * <p>
 * NOTE: This file has been copied from {@code org.springframework.lang}.
 *
 * @see NonNullApi
 * @see NonNullFields
 * @see NonNull
 */
@Internal
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Nonnull(when = When.MAYBE)
@TypeQualifierNickname
public @interface Nullable {

}