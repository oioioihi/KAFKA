package com.playground.controller;

import com.playground.infrastructure.kafka.producer.HelloProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloProducer helloProducer;

    @PostMapping("/api/hello")
    ResponseEntity<Void> hello() {

        helloProducer.helloProduce();

        return new ResponseEntity(HttpStatus.OK);
    }
}
