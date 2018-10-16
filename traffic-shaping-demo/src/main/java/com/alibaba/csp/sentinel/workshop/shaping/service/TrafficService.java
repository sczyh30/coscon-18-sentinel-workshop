package com.alibaba.csp.sentinel.workshop.shaping.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import org.springframework.stereotype.Service;

/**
 * @author Eric Zhao
 */
@Service
public class TrafficService {

    private final ExecutorService pool = Executors.newFixedThreadPool(16);

    public void initiateTrafficSurge() {
        for (int i = 0; i < 20; i++) {
            pool.submit(this::doSomething);
        }
    }

    public void doSomething() {
        Entry entry = null;
        try {
            entry = SphU.entry("doSomething");
            System.out.println("Do something at " + System.currentTimeMillis());
        } catch (BlockException ex) {
            ex.printStackTrace();
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }
}
