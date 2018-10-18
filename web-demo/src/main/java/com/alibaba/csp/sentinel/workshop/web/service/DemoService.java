package com.alibaba.csp.sentinel.workshop.web.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import org.springframework.stereotype.Service;

/**
 * @author Eric Zhao
 */
@Service
public class DemoService {

    @SentinelResource(value = "greetingFor", blockHandler = "handleBlockedForGreeting")
    public String greetingFor(String name) {
        return "Greeting: " + name;
    }

    public String handleBlockedForGreeting(String name, BlockException ex) {
        ex.printStackTrace();
        return "Blocked: " + name;
    }
}
