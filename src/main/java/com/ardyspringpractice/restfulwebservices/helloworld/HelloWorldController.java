package com.ardyspringpractice.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/helloworld")
    public String helloWorld() {
        return "Hello World!";
    }


    private MessageSource messageSource;
    public HelloWorldController (MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/helloworldi18n")
    public String helloWorldi18n() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
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
