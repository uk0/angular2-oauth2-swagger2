package com.sparksdev.flo.user.rest;

import org.springframework.web.bind.annotation.*;
import com.sparksdev.flo.common.service.BaseService;
import com.sparksdev.flo.domain.user.User;
import com.sparksdev.flo.domain.user.dto.UserDto;
import com.sparksdev.flo.user.api.user.UserApi;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

/**
 * @author bengill
 */
@RestController
@RequestMapping("/users")
public class UserRestController extends BaseService {

    @Inject
    UserApi userApi;


    @ApiOperation(value = "Get users",
            authorizations = {
                    @Authorization(
                            value="oauth2schema",
                            scopes = { @AuthorizationScope(scope = "global", description="This is a description...") }
                    )
            }
    )
    @RequestMapping(method= RequestMethod.GET)
    public UserDto[] getAll() {
        final List<UserDto> users = Arrays.asList(new UserDto("admin"));
        LOG.info("users " + users.size());
        System.out.println("users " + users.size());

        UserDto[] array = new UserDto[users.size()];
        users.toArray(array);
        return array;
    }

    @RequestMapping(method=RequestMethod.POST)
    public User create(@RequestBody User contact) {
        return null;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="{id}")
    public void delete(@PathVariable String id) {

    }

    @RequestMapping(method= RequestMethod.PUT, value="{id}")
    public User update(@PathVariable String id, @RequestBody User contact) {
        return null;
    }

}