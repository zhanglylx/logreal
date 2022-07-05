package com.alibaba.logreal.common.config;

import com.alibaba.logreal.event.LogEvent;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/10 21:43
 */
@Configuration
public class LogConfig {

    @Bean
    public Map<String, LogEvent> getChannels() {
        return new ConcurrentHashMap<>();
    }



    /**
     * 对于每一个订阅者是单线程的
     *
     * @return
     */
    @Bean
    public EventBus getEventBus() {
        return new EventBus();
    }

}
