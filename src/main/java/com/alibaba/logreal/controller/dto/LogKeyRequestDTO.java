package com.alibaba.logreal.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/18 16:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class LogKeyRequestDTO extends BaseRequestDTO {
    private boolean all;

    public boolean getAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }
}
