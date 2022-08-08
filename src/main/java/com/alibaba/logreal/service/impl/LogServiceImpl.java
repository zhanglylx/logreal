package com.alibaba.logreal.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.logreal.common.result.HttpCodeEnum;
import com.alibaba.logreal.common.result.Result;
import com.alibaba.logreal.common.utils.RandomUtils;
import com.alibaba.logreal.controller.dto.LogKeyRequestDTO;
import com.alibaba.logreal.controller.dto.LogRequestDTO;
import com.alibaba.logreal.event.LogEvent;
import com.alibaba.logreal.service.LogService;
import com.google.common.eventbus.EventBus;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.zly.utils.result.BaseResult;
import org.zly.utils.result.BaseResultFactoryImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/10 21:57
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class LogServiceImpl implements LogService {

    @Autowired
    private Map<String, LogEvent> logsMap;

    //    观察者设计模式
    @Autowired
    private EventBus eventBus;

    @Autowired
    private BlockingDeque<LogRequestDTO> queue;

    @Autowired
    private ThreadPoolExecutor threadPool;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
//        初始化一个线程，循环的将上传的日志通知观察者
//        如果直接在接口里添加，eventBus通知唤起等待线程时是不公平的
//        日志必须要保证顺序性,所以采用通过队列的方式
        this.threadPool.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    try {
                        LogRequestDTO logRequestDTO = queue.poll(60 * 1000, TimeUnit.MILLISECONDS);
                        if (logRequestDTO != null) {
                            eventBus.post(logRequestDTO);
                        }
                    } catch (Exception e) {
                        log.error("日志加入缓冲队列失败", e);
                    }
                }
            }
        });
    }

    @Override
    public BaseResult<Object> logHandler(LogRequestDTO logRequestDTO) {
        this.queue.offer(logRequestDTO);
        return new BaseResultFactoryImpl<>().createSuccess(null);
    }

    private final Object lock = new Object();

    @Override
    public BaseResult<Object> getKeyHandler(LogKeyRequestDTO baseRequestDTO) {
        String key = RandomUtils.getUUID();
        Queue<String> queue = (Queue<String>) applicationContext.getBean("observerQueue");
        LogEvent logEvent = new LogEvent(key, baseRequestDTO, queue, System.currentTimeMillis());
        this.logsMap.put(key, logEvent);
        this.eventBus.register(logEvent);
        return new BaseResultFactoryImpl<>().createSuccess(key);
    }

    @Override
    public BaseResult<JSONArray> getContentHandler(String key) {
        LogEvent logEvent = this.logsMap.get(key);
        if (logEvent != null) {
            Queue<String> queue = logEvent.getQueue();
            if (queue != null) {
                logEvent.setSurvivalTime(System.currentTimeMillis());
                JSONArray jsonArray = new JSONArray();
                while (queue.size() > 0) {
//                    jsonArray.add(queue.poll());
                    jsonArray.add("测试");
                }
                return new BaseResultFactoryImpl<JSONArray>().createSuccess(jsonArray);
            }
        }
        return new BaseResultFactoryImpl<JSONArray>().createErrorCommon("资源不存在");
    }
}
