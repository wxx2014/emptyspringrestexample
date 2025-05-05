package com.wedevol.emptyspringrest.controller;


import com.wedevol.emptyspringrest.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * User REST Controller
 *
 * @author charz
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    private static final User USER_1 = new User(1L, "Carlos", Arrays.asList("charz"), 26, "charz@yopmail.com");

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Long userId) throws Exception {
        LOG.info("Find user by id: {}", userId);
        if (userId.equals(1L)) {
            return USER_1;
        } else {
            throw new Exception("I just created 1 user to test!");
        }
    }

}
