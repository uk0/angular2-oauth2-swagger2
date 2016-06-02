/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sparksdev.flo.xml.parser.stream;

import static com.sparksdev.flo.strings.StringUtils.toCamelCase;

import com.thoughtworks.xstream.mapper.ClassAliasingMapper;
import com.thoughtworks.xstream.mapper.Mapper;

/**
 * @author bengill
 */
public class FloClassAliasingMapper extends ClassAliasingMapper {

    public FloClassAliasingMapper(Mapper wrapped) {
        super(wrapped);
    }

    @Override
    public Class realClass(String elementName) {
        try {
            return null;
        } catch (IllegalArgumentException e) {
            // do nothing we fall back on super's implementation
        }
        return super.realClass(elementName);
    }

    @Override
    public String serializedClass(Class type) {
        try {
            return toCamelCase(type.getSimpleName());
        } catch (IllegalArgumentException e) {
            // do nothing we fall back on super's implementation
        }
        return super.serializedClass(type);
    }
}
