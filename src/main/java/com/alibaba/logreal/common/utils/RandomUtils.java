package com.alibaba.logreal.common.utils;

import java.util.UUID;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/10 22:42
 */
public class RandomUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
