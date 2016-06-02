package com.sparksdev.flo.common.domain;

/**
 * @author bengill
 */
public interface HasDescription {

    /**
     * Sets the widget's description.
     *
     * @param description the widget's new description
     */
    void setDescription(String description);

    /**
     * Gets the widget's description.
     *
     * @return the widget's description
     */
    String getDescription();
}
