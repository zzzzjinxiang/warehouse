package com.yeqifu.bus.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectUtil {
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) throws NoSuchMethodException {
        Method method;
        try {
            if (paramTypes == null) {
                method = clazz.getDeclaredMethod(methodName, (Class<?>[]) null);
            } else {
                method = clazz.getDeclaredMethod(methodName, paramTypes);
            }
            return method;
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass().equals(Object.class)) {
                throw e;
            }
            return getMethod(clazz.getSuperclass(), methodName, paramTypes);
        }
    }

    public static List<Field> getFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (!clazz.getSuperclass().equals(Object.class)) {
            Field[] fields = clazz.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
        }
        return fieldList;
    }
}
