package com.ardyspringpractice.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/helloworld")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping(path = "/helloworld-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World!");
    }

    // Path Param
    @GetMapping(path = "helloworld/path-var/{name}")
    public HelloWorldBean HelloWorldPathVar(@PathVariable String name) {
        return new HelloWorldBean("Hello World! " + name);
    }
}
