package com.sparksdev.flo.common.domain;


/**
 * @author bengill
 */
public class SessionId extends AbstractId implements UserContextId {

    public SessionId(final String userId) {
        super(userId, IdType.SESSION);
    }

    @Deprecated
    public SessionId() {}
}
