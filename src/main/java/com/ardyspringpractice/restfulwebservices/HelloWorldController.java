package com.ardyspringpractice.restfulwebservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {
    @RequestMapping(method = RequestMethod.GET, path = "/helloworld")
    public String helloWorld() {
        return "Hello World!";
    }
}
