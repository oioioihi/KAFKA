package com.playground.config;

import com.playground.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

@Slf4j
@Configuration
public class ConsumerConfig {
    private final Executor taskExecutor;

    public ConsumerConfig(@Qualifier("hello-taskExecutor") Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Bean
    public Consumer<String> helloTopic(HelloService helloService) {
        log.info("Consume Thread : {} ", Thread.currentThread().getName());
        return helloService::helloConsume;
    }

}
