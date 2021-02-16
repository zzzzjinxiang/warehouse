package com.yeqifu.bus.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class Idepotent {

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void checkIsTheSameOne(Object src, Object tar, Field[] fields) {
        if (!src.getClass().getCanonicalName().equals(tar.getClass().getCanonicalName())){
            // 比较两个对象类型，不一致则直接抛类型不一致异常
        }

        String tempFieldName = null;
        try{
            boolean fieldDiffValue = false;
            Set<String> cacheSet = new HashSet<>();
            for (Field field:fields) {
                tempFieldName = field.getName();
                logger.info(field.getName());
                field.setAccessible(true);
                if(checkFieldIsNull(field, src, tar) && !field.get(src).equals(field.get(tar))) {
                    // 检查field的性质
                    // 1.主键则抛出主键异常
                    // 2.json串则比较对应值
                    // 比较json串

                } else if(!field.get(src).equals(field.get(tar))) {
                    fieldDiffValue = true;
                    cacheSet.add(field.getName());
                    logger.info("不同属性名:"+field.getName());
                    // 抛一个异常，这里可以优化，将所有不同属性名打个日志
                }
                if(fieldDiffValue) {
                    StringBuilder sb = new StringBuilder();
                    for(String fileName:cacheSet) {
                        sb.append(fileName).append(".");
                    }
                    sb.append("]");
                }
            }
        } catch (IllegalAccessException | NullPointerException e) {
            logger.error("非法", e);
            // 抛出自定义异常
        }
    }

    public boolean checkFieldIsNull(Field field, Object... a) {
        Type type = field.getType();
        // 数字类型一个为空则全为空，其他类型必须判定是否全为空
        return false;
    }

    public void compareJson(JSONObject a, JSONObject b, Set<String> set) {
        for(String key:a.keySet()){

        }
    }
}
