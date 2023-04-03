package com.piyush.springcloud.sleuthdemo.service;

import brave.Span;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TraceService {

    private final Tracer tracer;

    @Autowired
    public TraceService(Tracer tracer) {
        this.tracer = tracer;
    }

    public void work() throws InterruptedException {
        log.info("inside some work");
        Thread.sleep(2000L);
    }

    public void doSomeCustomSpan(){
        log.info("I am the same span");
        Span span = tracer.nextSpan().name("newCustomSpan").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(span.start())) {
            Thread.sleep(1000L);
            log.info("now in Another span");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            span.finish();
        }
        log.info("I am in the original span");
    }
}
