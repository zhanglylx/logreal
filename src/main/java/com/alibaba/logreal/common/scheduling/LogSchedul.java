package com.alibaba.logreal.common.scheduling;

import com.alibaba.logreal.common.config.LogEventConfig;
import com.alibaba.logreal.event.LogEvent;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/17 22:53
 */
@Component
@Slf4j
public class LogSchedul {
    @Autowired
    private EventBus eventBus;

    @Autowired
    private Map<String, LogEvent> logsMap;
    @Autowired
    private LogEventConfig logEventConfig;

    @Scheduled(cron = "0/20 * * * * ? ")
    public void removeLogObserver() {
        log.info("开始清除过期的观察者,logsMap大小:" + logsMap.size());
        final Iterator<Map.Entry<String, LogEvent>> iterator = logsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, LogEvent> map = iterator.next();
            LogEvent logEvent = map.getValue();
            if ((System.currentTimeMillis() - logEvent.getSurvivalTime()) >= this.logEventConfig.getSurvivalTime()) {
                this.eventBus.unregister(logEvent);
                iterator.remove();
//                log.info("清除：" + logEvent.getKey());
            }
        }
        log.info("清除完成，logsMap大小:" + logsMap.size());
    }
}
