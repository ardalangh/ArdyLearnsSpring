package com.ardy.learnsspring.limitsservice.controller;

import com.ardy.learnsspring.limitsservice.bean.Limits;
import com.ardy.learnsspring.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits getLimits() {
//        return new Limits(1, 1000);
        return new Limits(configuration.getMin(), configuration.getMax());
    }
}
