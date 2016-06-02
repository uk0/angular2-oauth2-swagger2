package com.sparksdev.flo.common.dto.validation;

/**
 * @author bengill
 */
public class NullCheck {

    public static void checkNotNull(final Object obj, final String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message + " cannot be null");
        }
    }
}
