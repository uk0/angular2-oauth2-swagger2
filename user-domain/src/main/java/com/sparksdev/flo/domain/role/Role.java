package com.sparksdev.flo.domain.role;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import com.sparksdev.flo.common.domain.BaseEntity;
import com.sparksdev.flo.common.domain.HasDescription;
import com.sparksdev.flo.common.domain.HasName;
import com.sparksdev.flo.common.domain.RoleId;

/**
 * @author bengill
 */
@Entity
@Document(collection = "role")
public class Role extends BaseEntity implements GrantedAuthority, HasName, HasDescription {

    @Indexed
    private String name;

    /** Description of the role. */
    private String description;




    public Role(final String name) {
        this.name = name;
    }



    @Deprecated
    public Role() {
        super();
    }

    public RoleId getRoleId() {
        if (getId() != null) {
            return new RoleId(getId());
        }
        return null;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns user name for this user.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



    @Override public String getAuthority() {
        return getName();
    }
}
