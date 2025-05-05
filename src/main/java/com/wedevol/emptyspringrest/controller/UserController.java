package com.wedevol.emptyspringrest.controller;


import java.util.Arrays;

import com.wedevol.emptyspringrest.service.WxyiyanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.wedevol.emptyspringrest.entity.User;

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

    @Autowired
    private WxyiyanService wxyiyanService;

    @RequestMapping(value = "/getByAI/{context}", method = RequestMethod.GET)
    public String getByAI(@PathVariable String context) throws Exception{
        return wxyiyanService.talk(context);
    }



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
