package com.alibaba.csp.sentinel.workshop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Run with: {@code -Dcsp.sentinel.api.port=8723 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=web-demo}.
 *
 * @author Eric Zhao
 */
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
