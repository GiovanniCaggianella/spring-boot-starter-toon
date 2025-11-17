package com.giovannicaggianella.toon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method return type to be serialized to TOON format.
 * Automatically serializes the response object to TOON format using JToon.
 *
 * Usage:
 * <pre>
 * @GetMapping("/users/{id}")
 * @ToonResponse
 * public User getUser(@PathVariable Long id) {
 *     // Response is automatically serialized to TOON format
 *     return userService.findById(id);
 * }
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToonResponse {

    /**
     * Optional content type for the response.
     * Default is "application/toon" or "text/plain" depending on configuration.
     */
    String contentType() default "application/toon";

    /**
     * Whether to include length markers in array serialization.
     * When true, arrays will be prefixed with # to indicate count.
     */
    boolean lengthMarker() default false;

}
