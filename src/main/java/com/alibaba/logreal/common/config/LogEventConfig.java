package com.alibaba.logreal.common.config;

import com.alibaba.logreal.controller.dto.BaseRequestDTO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/17 23:11
 */
@Configuration
@ConfigurationProperties(prefix = "spring.log-event")
@Data
public class LogEventConfig {
    private long survivalTime;

}
