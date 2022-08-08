package com.alibaba.logreal.service;

import com.alibaba.logreal.common.result.Result;
import com.alibaba.logreal.controller.dto.LogKeyRequestDTO;
import com.alibaba.logreal.controller.dto.LogRequestDTO;
import org.zly.utils.result.BaseResult;

import java.util.List;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/10 21:56
 */
public interface LogService {

    BaseResult<Object> logHandler(LogRequestDTO logRequestDTO);

    BaseResult<Object> getKeyHandler(LogKeyRequestDTO baseRequestDTO);

    BaseResult<List<Object>> getContentHandler(String key);
}
