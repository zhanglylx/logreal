package com.alibaba.logreal.event;

import com.alibaba.logreal.common.utils.ReflectUtils;
import com.alibaba.logreal.controller.dto.BaseRequestDTO;
import com.alibaba.logreal.controller.dto.LogKeyRequestDTO;
import com.alibaba.logreal.controller.dto.LogRequestDTO;
import com.google.common.eventbus.Subscribe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Queue;

/**
 * 日志观察者事件
 *
 * @author zly
 * @version 1.0
 * @date 2021/4/17 11:06
 */

public class LogEvent {
    //    事件标识符
    private String key;
    //    存储事件内容的队列
    private Queue<String> queue;
    //    条件
    private LogKeyRequestDTO baseRequestDTO;
    //    存活时间
    private long survivalTime;
    //    条件匹配表
    private static final List<Method> CONDITION_BASE;

    //    使用享元模式预先缓存，较少反射带来的性能消耗
    static {
        CONDITION_BASE = ReflectUtils.getMemberPublicMethods(BaseRequestDTO.class);
    }

    public LogEvent(String key, LogKeyRequestDTO baseRequestDTO, Queue<String> queue, long survivalTime) {
        this.key = key;
        this.queue = queue;
        this.baseRequestDTO = baseRequestDTO;
        this.survivalTime = survivalTime;
    }

    @Subscribe
    public void observer(LogRequestDTO logRequestDTO) throws  InvocationTargetException, IllegalAccessException {
        if (baseRequestDTO.getAll() || conditionBase(logRequestDTO)) {
            this.queue.offer(logRequestDTO.getData());
        }

    }

    /**
     * 匹配条件，判断基础的条件与目标中的条件值是否先沟通
     *
     * @param target 目标
     * @return 相同返回true
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public boolean conditionBase(BaseRequestDTO target) throws InvocationTargetException, IllegalAccessException {
        Object o;
        for (Method method : CONDITION_BASE) {
            o = method.invoke(this.baseRequestDTO);
            if (o != null  && !o.equals(method.invoke(target))){
                return false;
            }
        }
        return true;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }

    public LogKeyRequestDTO getBaseRequestDTO() {
        return baseRequestDTO;
    }

    public void setBaseRequestDTO(LogKeyRequestDTO baseRequestDTO) {
        this.baseRequestDTO = baseRequestDTO;
    }

    public long getSurvivalTime() {
        return survivalTime;
    }

    public void setSurvivalTime(long survivalTime) {
        this.survivalTime = survivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEvent logEvent = (LogEvent) o;
        return key != null ? key.equals(logEvent.key) : logEvent.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
