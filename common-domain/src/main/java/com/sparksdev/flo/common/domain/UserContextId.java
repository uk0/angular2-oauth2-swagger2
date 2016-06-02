package com.sparksdev.flo.common.domain;

import java.io.Serializable;

/**
 * Marker interface for ids that can be used for setting request context.
 *
 * @author bengill
 */
public interface UserContextId extends Serializable {

    String getId();
}
