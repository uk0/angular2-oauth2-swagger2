package com.sparksdev.flo.common.domain;

/**
 * @author bengill
 */
public interface HasName {

    /**
     * Sets the widget's name.
     *
     * @param name the widget's new name
     */
    void setName(String name);

    /**
     * Gets the widget's name.
     *
     * @return the widget's name
     */
    String getName();
}
