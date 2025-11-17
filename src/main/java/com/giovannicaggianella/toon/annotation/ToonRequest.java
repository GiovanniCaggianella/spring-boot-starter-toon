package com.giovannicaggianella.toon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method parameter as a TOON-formatted request body.
 * Automatically deserializes TOON format to the target object using JToon.
 *
 * Usage:
 * <pre>
 * @PostMapping("/users")
 * public ResponseEntity<User> createUser(@ToonRequest User user) {
 *     // user is automatically deserialized from TOON format
 *     return ResponseEntity.ok(user);
 * }
 * </pre>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToonRequest {

    /**
     * Optional custom name for the request body parameter.
     */
    String name() default "";

    /**
     * Whether the request body is required.
     */
    boolean required() default true;

}
