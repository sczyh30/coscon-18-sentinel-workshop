package com.alibaba.csp.sentinel.workshop.dubbo.controller;

import com.alibaba.csp.sentinel.slots.block.SentinelRpcException;
import com.alibaba.csp.sentinel.workshop.dubbo.FooService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eric Zhao
 */
@RestController
@DubboComponentScan
public class DemoController {

    @Reference(url = "dubbo://127.0.0.1:25758", timeout = 3000)
    private FooService fooService;

    @GetMapping("/hello")
    public String apiSayHello(@RequestParam String name) {
        try {
            return fooService.sayHello(name);
        } catch (SentinelRpcException e) {
            e.getCause().printStackTrace();
            return "oops, blocked...";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "oops...";
        }
    }
}
