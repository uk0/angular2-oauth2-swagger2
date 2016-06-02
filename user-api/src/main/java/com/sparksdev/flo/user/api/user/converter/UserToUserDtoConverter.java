package com.sparksdev.flo.user.api.user.converter;

import org.springframework.core.convert.converter.Converter;
import com.sparksdev.flo.domain.user.User;
import com.sparksdev.flo.domain.user.dto.UserDto;

/**
 * @author bengill
 */
public class UserToUserDtoConverter implements Converter<User, UserDto> {


    @Override public UserDto convert(final User user) {
        return new UserDto(user.getUsername());
    }
}
