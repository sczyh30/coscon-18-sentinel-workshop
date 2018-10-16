package com.alibaba.csp.sentinel.workshop.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eric Zhao
 */
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String apiHello() {
        return "Hello at " + System.currentTimeMillis();
    }

    @GetMapping("/greeting")
    public String apiGreetingFor(@RequestParam String name) {
        return "Greeting: " + name;
    }
}
