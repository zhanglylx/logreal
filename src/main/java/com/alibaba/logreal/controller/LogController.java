package com.alibaba.logreal.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.logreal.common.result.HttpCodeEnum;
import com.alibaba.logreal.common.result.Result;
import com.alibaba.logreal.controller.dto.LogKeyRequestDTO;
import com.alibaba.logreal.controller.dto.LogRequestDTO;
import com.alibaba.logreal.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zly.utils.result.BaseResult;
import org.zly.utils.result.BaseResultFactoryImpl;

import java.util.List;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/10 11:43
 */
@RestController
public class LogController {

    @Autowired
    private LogService logService;


    @PostMapping(value = "/uploadLog", produces = "application/json;charset=utf-8")
    public BaseResult<Object> uploadLog(@Validated LogRequestDTO logRequestDTO) {
        return logService.logHandler(logRequestDTO);
    }

    /**
     * 获取key
     * 给将要获取的内容添加一个key，然后通过key请求具体的内容
     *
     * @param baseRequestDTO
     * @return
     */
    @RequestMapping(value = "/getKey", produces = "application/json;charset=utf-8")
    public BaseResult<Object> getKey(LogKeyRequestDTO baseRequestDTO) {
        return logService.getKeyHandler(baseRequestDTO);
    }

    @RequestMapping(value = "/getContent", produces = "application/json;charset=utf-8")
    public BaseResult<JSONArray> getContent(String key) {
        if (StringUtils.isEmpty(key)) return new BaseResultFactoryImpl<JSONArray>().createErrorParameter(key);
        return logService.getContentHandler(key);
    }

}
