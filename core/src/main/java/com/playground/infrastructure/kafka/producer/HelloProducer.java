package com.playground.infrastructure.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelloProducer {

    private final StreamBridge streamBridge;

    @Async
    public void helloProduce() {
        streamBridge.send(Topics.HELLO_TOPIC, "hello");
    }

}
