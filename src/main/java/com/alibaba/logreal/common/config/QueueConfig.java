package com.alibaba.logreal.common.config;

import com.alibaba.logreal.controller.dto.LogRequestDTO;
import com.alibaba.logreal.event.LogEvent;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/17 22:35
 */
@Configuration
@ConfigurationProperties(prefix = "spring.queue")
@Data
public class QueueConfig {
    private int queueCapacity;

    @Bean("observerQueue")
    @Scope("prototype")
    public Queue<String> getQueue() {
        return new LinkedBlockingDeque<>(this.queueCapacity);
    }

    @Bean
    public BlockingDeque<LogRequestDTO> getQueueLogs() {
        return new LinkedBlockingDeque<>(this.queueCapacity);
    }
}

