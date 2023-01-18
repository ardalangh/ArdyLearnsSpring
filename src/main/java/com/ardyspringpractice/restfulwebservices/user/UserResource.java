package com.ardyspringpractice.restfulwebservices.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return service.findAll();
    }

    @GetMapping(path = "/users/{userId}")
    public User getAllUsers(@PathVariable int userId) {
        return service.findOne(userId);
    }
}
