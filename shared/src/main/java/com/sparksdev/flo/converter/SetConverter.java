/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sparksdev.flo.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bengill
 */
public class SetConverter<S, T> {


    /**
     * Convert the source of type S to target type T.
     *
     * @param sourceList
     *         the source object to convert, which must be an instance of S (never {@code null})
     * @return the converted object, which must be an instance of T (potentially {@code null})
     * @throws IllegalArgumentException
     *         if the source could not be converted to the desired target type
     */
    public static <T, S> Set<T> convert(final Set<S> sourceList, final Converter<S, T> converter) {

        if (sourceList == null) {
            return null;
        }

        Set<T> targets = new HashSet<>();

        for (S source : sourceList) {
            targets.add(converter.convert(source));
        }
        return targets;
    }
}

