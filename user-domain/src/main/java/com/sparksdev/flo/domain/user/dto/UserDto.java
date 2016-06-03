package com.sparksdev.flo.domain.user.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * @author bengill
 */
@ApiModel(value="UserDto", description="Sample model for the user")
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
