package com.sparksdev.flo.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author bengill
 */
public abstract class AbstractIdDto implements Serializable {

    private String id;

    private IdTypeDto idTypeDto;

    public AbstractIdDto(final String id, final IdTypeDto idTypeDto) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (idTypeDto == null) {

            throw new IllegalArgumentException("id cannot be null");

        }
        this.id = id;
        this.idTypeDto = idTypeDto;
    }

    @Deprecated
    public AbstractIdDto() {}

    public static <ID extends AbstractIdDto> Collection<String> convert(final Collection<ID> ids) {

        Collection<String> stringIds = new ArrayList();

        for (ID id : ids) {
            stringIds.add(id.getId());
        }
        return stringIds;
    }

    public IdTypeDto getIdTypeDto() {
        return idTypeDto;
    }

    public String getId() {
        return id;
    }

    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractIdDto)) {
            return false;
        }

        final AbstractIdDto that = (AbstractIdDto) o;

        return getId().equals(that.getId());

    }

    @Override public int hashCode() {
        return getId().hashCode();
    }
}
