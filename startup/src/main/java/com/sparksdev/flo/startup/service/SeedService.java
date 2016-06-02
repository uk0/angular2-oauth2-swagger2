/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sparksdev.flo.startup.service;

import org.springframework.stereotype.Service;
import com.sparksdev.flo.constants.Constants;
import com.sparksdev.flo.domain.user.User;
import com.sparksdev.flo.user.api.user.UserApi;

import javax.inject.Inject;

/**
 * @author bengill
 */
@Service
public class SeedService {



    @Inject
    private UserApi userApi;








    /**
     * This method will loadDemoData the default admin user.
     *
     * We will probably move to a reference data type service.
     *
     */
    public void seed() {

        loadData();

    }

    private void loadData() {


        createRootUser();

    }



    /**
     *
     */
    private void createRootUser() {

        User user = new User();
        user.setUsername(Constants.ROOT_ADMIN_USER_NAME);
        user.setFirstName(Constants.ROOT_ADMIN_FIRST_NAME);
        user.setLastName(Constants.ROOT_ADMIN_SURNAME);
        user.setPassword(Constants.ROOT_ADMIN_PASSWORD);
        user.setEnabled(true);

        userApi.create(user);
    }
}
