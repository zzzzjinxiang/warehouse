package com.yeqifu.bus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CacheReflectUtil {

    private static HashMap<String, Method> methodMap = new HashMap<>();
    private static HashMap<String, Field> fieldMap = new HashMap<>();
    private static HashMap<String, Class<?>> classMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(CacheReflectUtil.class.getSimpleName());

    public static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> superClass = clazz;
        while (superClass != Object.class) {
            try {
                String key = getFieldPro(clazz, fieldName);
                if (fieldMap.containsKey(key)) {
                    return fieldMap.get(key);
                } else {
                    Field field = superClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    fieldMap.put(key, field);
                    return field;
                }
            } catch (NoSuchFieldException e) {
                return getField(superClass.getSuperclass(), fieldName);
            } catch (SecurityException e) {
                logger.error("security", e);
                throw e;
            }
        }
        throw new NoSuchFieldException(fieldName);
    }

    public static List<Field> getFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        Class<?> superClass = clazz;
        while (superClass != Object.class) {
            Field[] declaredFields = superClass.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                fieldMap.put(getFieldPro(clazz, field.getName()), field);
                list.add(field);
            }
            superClass = superClass.getSuperclass();
        }
        return list;
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) throws NoSuchMethodException {
        String key = getMethodName(clazz, methodName, paramTypes);
        if (methodMap.containsKey(key)) {
            return methodMap.get(key);
        } else {
            Method method = ReflectUtil.getMethod(clazz, methodName, paramTypes);
            method.setAccessible(true);
            methodMap.put(key, method);
            return method;
        }
    }

    private static String getFieldPro(Class<?> clazz, String fieldName) {
        return clazz.getName() + "." + fieldName;
    }

    public static String getMethodName(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(clazz.getName());
        buffer.append(".");
        buffer.append(methodName);
        if (paramTypes == null || paramTypes.length == 0) {
            buffer.append("()");
        } else {
            buffer.append("(");
            for (Class<?> paraType : paramTypes) {
                buffer.append(paraType.getName());
                buffer.append(",");
            }
            buffer.setLength(buffer.length() - 1);
            buffer.append(")");
        }
        return buffer.toString();
    }

}
