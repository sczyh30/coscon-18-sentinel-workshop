package com.alibaba.csp.sentinel.workshop.shaping;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

/**
 * @author Eric Zhao
 */
public class UniformRateLimiterDemo {

    private static int count = 2;

    private final ExecutorService pool = Executors.newFixedThreadPool(16);

    public static void main(String[] args) throws Exception {
        UniformRateLimiterDemo demo = new UniformRateLimiterDemo();
        demo.initiateTrafficSurge();
        Thread.sleep(20000);
        System.out.println("=======================");

        demo.initNormalFlowRule();
        demo.initiateTrafficSurge();

        Thread.sleep(5000);
        System.out.println("============");

        demo.initRateLimitingFlowRule();
        demo.initiateTrafficSurge();
    }

    private void initNormalFlowRule() {
        FlowRule rule = new FlowRule();
        rule.setResource("doSomething");
        rule.setCount(count);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        FlowRuleManager.loadRules(Collections.singletonList(rule));
    }

    private void initRateLimitingFlowRule() {
        FlowRule rule = new FlowRule();
        rule.setResource("doSomething");
        rule.setCount(count);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        rule.setMaxQueueingTimeMs(5000);
        FlowRuleManager.loadRules(Collections.singletonList(rule));
    }

    public void initiateTrafficSurge() throws Exception {
        for (int i = 0; i < 15; i++) {
            pool.submit(this::doSomething);
            Thread.sleep(5);
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
