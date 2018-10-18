package com.alibaba.csp.sentinel.workshop.web.controller;

import com.alibaba.csp.sentinel.workshop.web.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eric Zhao
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/hello")
    public String apiHello() {
        return "Hello at " + System.currentTimeMillis();
    }

    @GetMapping("/greeting")
    public String apiGreetingFor(@RequestParam String name) {
        return demoService.greetingFor(name);
    }
}
