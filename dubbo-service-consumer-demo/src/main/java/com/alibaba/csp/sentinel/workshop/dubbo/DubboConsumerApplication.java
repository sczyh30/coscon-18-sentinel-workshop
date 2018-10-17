package com.alibaba.csp.sentinel.workshop.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Run with the parameter:
 * {@code -Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=8728
 * -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=dubbo-consumer}
 *
 * @author Eric Zhao
 */
@SpringBootApplication(scanBasePackages = "com.alibaba.csp.sentinel.workshop.dubbo.*")
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
}
