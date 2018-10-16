package com.alibaba.csp.sentinel.workshop.shaping.controller;

import com.alibaba.csp.sentinel.workshop.shaping.service.TrafficService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eric Zhao
 */
@RestController
public class DemoController {

    @Autowired
    private TrafficService trafficService;

    @GetMapping("/ping")
    public String apiDo() {
        trafficService.initiateTrafficSurge();
        return "pong";
    }
}
