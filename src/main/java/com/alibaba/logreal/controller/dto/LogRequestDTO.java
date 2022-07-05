package com.alibaba.logreal.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/10 21:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogRequestDTO extends BaseRequestDTO {
    private String data;
}

