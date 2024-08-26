package com.playground.infrastructure.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;


@Slf4j
@Component
@RequiredArgsConstructor
public class HelloProducer {

    private final StreamBridge streamBridge;

    @Async
    public void helloProduce() {

        parallelProduce();
    }

    @Async
    public void parallelProduce() {

        IntStream.range(0, 10).parallel().forEach(i -> {
            log.info("producer thread : {} ", Thread.currentThread().getName());
            streamBridge.send(Topics.HELLO_TOPIC, "hello. " + i);
        });

    }

}
