package com.ardyspringpractice.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {
    @GetMapping(path = "/helloworld")
    public String helloWorld() {
        return "Hello World!";
    }
}
