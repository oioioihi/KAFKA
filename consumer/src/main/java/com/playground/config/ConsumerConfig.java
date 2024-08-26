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

    /**
     * 소요시간 : 약 2초
     * partition : 5개
     * concurrency : 5
     * 쓰레드가 5개가 바로 생성되어서 이벤트를 처리할 줄 알았지만, producer에서 병렬로 빠르게 이벤트를 발행해야 최대 4개의 스레드가 일을 한다.
     * 이벤트 수신 -> 남는 스레드 있어? -> 없으면 생성, 있으면 해당 스레드 재 사용 하는 형식으로 돌아감
     * 사용되는 Queue :
     */
    @Bean
    public Consumer<String> helloTopic(HelloService helloService) {
        return dto -> {
            log.info("Consume Thread : {} ", Thread.currentThread().getName());
            helloService.helloConsume(dto);
        };
    }


    /**
     * 소요시간 : 약 1.5초
     * partition : 5개
     * concurrency : 5
     * 이벤트를 수신하고 바로 worker thread에게 작업을 할당함
     * 지정한 taskExecutor내의 가용범위 안에서 병렬로 작업함
     * 사용되는 Queue : LinkedBlockingQueue (default)
     */
    @Bean
    public Consumer<String> helloTopic1(HelloService helloService) {

        return dto -> taskExecutor.execute(() -> {
            // 0.3초 만에 10번의 producer 이벤트를 처리함
            log.info("Consume Thread : {} ", Thread.currentThread().getName());
            helloService.helloConsume(dto);
        });
    }

}
