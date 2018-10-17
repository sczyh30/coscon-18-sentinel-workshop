package com.alibaba.csp.sentinel.workshop.dubbo.service;

import java.util.concurrent.ThreadLocalRandom;

import com.alibaba.csp.sentinel.workshop.dubbo.FooService;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Eric Zhao
 */
@Service
public class FooServiceImpl implements FooService {

    @Override
    public String sayHello(String name) {
        // Simulate slow invocations randomly.
        if (ThreadLocalRandom.current().nextInt(0, 100) > 70) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, " + name;
    }
}
