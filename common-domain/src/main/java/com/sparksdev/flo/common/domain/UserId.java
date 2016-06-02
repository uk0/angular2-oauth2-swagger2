package com.sparksdev.flo.common.domain;


/**
 * @author bengill
 */
public class UserId extends AbstractId implements UserContextId {

    public UserId(final String userId) {
        super(userId, IdType.USER);
    }

    @Deprecated
    public UserId() {}
}
