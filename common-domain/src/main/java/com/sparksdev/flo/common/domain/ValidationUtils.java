package com.sparksdev.flo.common.domain;

/**
 * @author bengill
 */
public class ValidationUtils {

    /**
     *
     * @param obj
     * @param message
     */
    public static void checkNotNull(final Object obj, final String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message + " cannot be null");
        }
    }
}