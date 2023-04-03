package com.piyush.springcloud.sleuthdemo.controller;

import com.piyush.springcloud.sleuthdemo.service.TraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TraceController {

    private final TraceService traceService;

    @Autowired
    public TraceController(TraceService traceService) {
        this.traceService = traceService;
    }

    @GetMapping("/")
    public String helloSleuth() throws InterruptedException {
        log.info("Hello from controller");
        traceService.work();
        return "Success";
    }

    @GetMapping("/custom-span")
    public String helloSleuthNewSpan(){
        log.info("Custom span");
        traceService.doSomeCustomSpan();
        return "Success";
    }

}
