package com.alibaba.logreal.common.utils;

import com.alibaba.logreal.controller.dto.BaseRequestDTO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/18 21:46
 */
public class ReflectUtils {

    /**
     * 获取class中属性的公共方法
     *
     * @param clazz
     * @return
     */
    public static List<Method> getMemberPublicMethods(Class<?> clazz) {
        List<Method> list = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            char[] cs = field.getName().toCharArray();
            cs[0] -= 32;
            String name = "get" + String.valueOf(cs);
            try {
                Method method = clazz.getDeclaredMethod(name);
                if (Modifier.isPublic(method.getModifiers())) {
                    list.add(method);
                }
            } catch (NoSuchMethodException ignored) {
            }
        }
        return list;
    }
}
