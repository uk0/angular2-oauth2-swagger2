package com.sparksdev.flo.common.dto;

import com.sparksdev.flo.common.domain.HasId;

import java.io.Serializable;

/**
 * @author bengill
 */
public abstract class BaseEntityDto implements Serializable, HasId, FloDto {

    private String id;

    /** Used for optimistic locking. */
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }
}
