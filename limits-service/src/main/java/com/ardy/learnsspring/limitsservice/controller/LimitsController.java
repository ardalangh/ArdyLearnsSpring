package com.ardy.learnsspring.limitsservice.controller;

import com.ardy.learnsspring.limitsservice.bean.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @GetMapping("/limits")
    public Limits getLimits() {
        return new Limits(1, 1000);
    }
}
