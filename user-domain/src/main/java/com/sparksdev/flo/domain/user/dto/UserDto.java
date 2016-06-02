package com.sparksdev.flo.domain.user.dto;

import java.io.Serializable;

/**
 * @author bengill
 */
public class UserDto implements Serializable {

    private String username;

    public UserDto(final String username) {
        this.username = username;
    }

    @Deprecated
    public UserDto() {}

    public String getUsername() {
        return username;
    }
}
