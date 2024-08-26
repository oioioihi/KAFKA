package com.playground.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelloService {

    public void helloConsume(String payload) {
        log.info("Pay Load : {}", payload);
    }
}
