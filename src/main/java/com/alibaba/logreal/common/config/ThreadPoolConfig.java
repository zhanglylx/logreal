package com.alibaba.logreal.common.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 默认线程池配置
 *
 * @author: tangyl
 * @since: 2020/8/6
 */
@Configuration
@ConfigurationProperties(prefix = "spring.thread-pool.default")
public class ThreadPoolConfig {

    /**
     * 核心线程数
     */
    private int corePoolSize;
    /**
     * 最大线程数
     */
    private int maximumPoolSize;
    /**
     * 线程存活时间
     */
    private Long keepAliveTime;
    /**
     * 队列容量
     */
    private int queueCapacity;

    /**
     * 默认任务线程池
     *
     * @return
     */
    @Bean(value = "defaultQueueThreadPool")
    public ThreadPoolExecutor buildDefaultQueueThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("default-queue-thread-%d").build();
        // 实例化线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(this.getCorePoolSize(), this.getMaximumPoolSize(), this.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(this.getQueueCapacity()), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
