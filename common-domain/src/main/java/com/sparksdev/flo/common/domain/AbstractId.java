package com.sparksdev.flo.common.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author bengill
 */

public abstract class AbstractId implements Serializable {

    @Id
    @Indexed
    private String id;

    /**
     *
     */
    @Indexed
    private IdType idType;

    public AbstractId(final String id, final IdType idType) {
        ValidationUtils.checkNotNull(id, "id");
        ValidationUtils.checkNotNull(idType, "idType");
        this.id = id;
        this.idType = idType;
    }

    @Deprecated
    public AbstractId() {}

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public IdType getIdType() {
        return idType;
    }



    public static <ID extends AbstractId> Collection<String> convert(final Collection<ID> ids) {

        Collection<String> stringIds = new ArrayList();

        for (ID id : ids) {
            stringIds.add(id.getId());
        }
        return stringIds;
    }


    public static AbstractId valueOf(final String id, final IdType idType) {

        switch (idType) {




            case USER: return new UserId(id);
            case ROLE_DEFINITION: return new RoleId(id);


            default: throw new IllegalStateException("Unrecognised owner type " + idType.name());
        }

    }

    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractId)) {
            return false;
        }

        final AbstractId that = (AbstractId) o;

        if (!getId().equals(that.getId())) {
            return false;
        }
        return getIdType() == that.getIdType();

    }

    @Override public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getIdType().hashCode();
        return result;
    }

    @Override public String toString() {
        return "AbstractId{" +
                "id='" + id + '\'' +
                ", idType=" + idType +
                '}';
    }
}
