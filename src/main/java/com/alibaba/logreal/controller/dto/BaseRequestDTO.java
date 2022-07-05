package com.alibaba.logreal.controller.dto;

import com.alibaba.logreal.common.utils.RandomUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/10 11:56
 */
@Data
public class BaseRequestDTO {
    @ApiModelProperty(value = "应用名", example = "shuqireader", required = true)
    @NotBlank(message = "应用名不能为空")
    private String appName;

    @ApiModelProperty(value = "版本", example = "11.3.7.135", required = true)
    @NotBlank(message = "版本不能为空")
    private String appVersion;

    @ApiModelProperty(value = "平台", example = "ios", required = true)
    @NotBlank(message = "平台不能为空")
    private String platform;

    @ApiModelProperty(value = "类型", example = "1(闪屏)", required = true)
    @NotNull(message = "类型不能为空")
    @NotBlank(message = "平台不能为空")
    private String type;

    @ApiModelProperty(value = "用户id", example = "1234567", required = true)
    @NotBlank(message = "用户id不能为空")
    private String userId;

}
