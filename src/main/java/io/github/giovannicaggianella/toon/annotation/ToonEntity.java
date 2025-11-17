package io.github.giovannicaggianella.toon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a Toon-serializable entity.
 * Enables automatic TOON format serialization/deserialization using JToon.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToonEntity {

    /**
     * Entity name for TOON serialization.
     * If not specified, the class name is used.
     */
    String name() default "";

    /**
     * Entity description.
     */
    String description() default "";

}
