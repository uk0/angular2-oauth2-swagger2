package com.sparksdev.flo.common.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;

/**
 * @author bengill
 */
public abstract class BaseEntity implements Serializable {

    /** The internal ID of the object. */
    @Id
    @Indexed
    private String id;

    @Indexed
    private IdType idType;

    /** Used for optimistic locking. */
    //@Version
    private Integer version;

    /** Whether this entity is deleted or not. */
    private boolean deleted;

    public BaseEntity(final String id, final IdType idType) {
        ValidationUtils.checkNotNull(id, "id");
        ValidationUtils.checkNotNull(idType, "idType");
        this.id = id;
        this.idType = idType;
    }

    @Deprecated
    public BaseEntity() {
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(final IdType idType) {
        this.idType = idType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hashtables such as those
     * provided by <code>java.util.Hashtable</code>.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }

        final BaseEntity that = (BaseEntity) o;

        if (!id.equals(that.id)) {
            return false;
        }
        if (idType != that.idType) {
            return false;
        }
        return getVersion().equals(that.getVersion());
    }

    @Override public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", idType=" + idType +
                ", version=" + version +
                ", deleted=" + deleted +
                '}';
    }
}
