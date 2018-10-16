package com.alibaba.csp.sentinel.workshop.shaping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Eric Zhao
 */
@SpringBootApplication
public class TrafficShapingApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TrafficShapingApplication.class, args);
    }
}
