package com.sparksdev.flo.common.domain;

/**
 * @author bengill
 */
public class RoleId extends AbstractId {

    public RoleId(final String id) {
        super(id, IdType.ROLE_DEFINITION);
    }

    @Deprecated
    public RoleId() {}
}
