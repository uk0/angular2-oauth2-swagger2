package com.sparksdev.flo.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bengill
 */
public class ListConverter<S, T> {


    /**
     * Convert the source of type S to target type T.
     *
     * @param sourceList
     *         the source object to convert, which must be an instance of S (never {@code null})
     * @return the converted object, which must be an instance of T (potentially {@code null})
     * @throws IllegalArgumentException
     *         if the source could not be converted to the desired target type
     */
    public static <T, S> List<T> convert(final List<S> sourceList, final Converter<S, T> converter) {

        if (sourceList == null) {
            return null;
        }

        List<T> targets = new ArrayList();

        for (S source : sourceList) {
            targets.add(converter.convert(source));
        }
        return targets;
    }
}

