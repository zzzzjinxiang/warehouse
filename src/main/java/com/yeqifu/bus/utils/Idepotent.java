package com.yeqifu.bus.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class Idepotent {

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    // 09c49f04cb6ed8a066a419a77d7a4c20eb196177-token
    public void checkIsTheSameOne(Object src, Object tar, Field[] fields) {
        if (!src.getClass().getCanonicalName().equals(tar.getClass().getCanonicalName())) {
            // 比较两个对象类型，不一致则直接抛类型不一致异常
        }

        String tempFieldName = null;
        try {
            boolean fieldDiffValue = false;
            Set<String> cacheSet = new HashSet<>();
            for (Field field : fields) {
                tempFieldName = field.getName();
                logger.info(field.getName());
                field.setAccessible(true);
                if (checkFieldIsNull(field, src, tar)) {
                    // 如果一个为空，或者另一个为““则认为相同
                } else if (!field.get(src).equals(field.get(tar))) {
                    // 检查field的性质
                    // 1.主键则抛出主键异常
                    // 2.json串则比较对应值
                    // 比较json串
                    fieldDiffValue = true;
                    cacheSet.add(field.getName());
                    logger.info("不同属性名:" + field.getName());
                    // 抛一个异常，这里可以优化，将所有不同属性名打个日志
                }
                if (fieldDiffValue) {
                    StringBuilder sb = new StringBuilder();
                    for (String fileName : cacheSet) {
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

    /**
     * json和普通对象比较器，将不相同的属性放在set中
     *
     * @param a
     * @param b
     * @param set
     */
    public void compareJson(JSONObject a, JSONObject b, Set<String> set) {
        for (String key : a.keySet()) {
            if (a instanceof JSONObject) {
                JSONObject aValue = a.getJSONObject(key);
                JSONObject bValue = b.getJSONObject(key);
                if (ObjectUtils.isEmpty(aValue) || ObjectUtils.isEmpty(bValue)) {
                    logger.info("根路径为空，不相等");
                    set.add(key);
                } else {
                    compareJson(aValue, bValue, set);
                }
            } else {
                if (!a.getString(key).equals(b.getString(key))) {
                    set.add(key);
                }
            }
        }
    }

    /**
     * 剔除excludeFields中的属性
     *
     * @param fields
     * @param excludeFields
     * @return 剔除后的fields
     */
    public Field[] fieldsExclude(Field[] fields, String[] excludeFields) {
        Set<String> fieldSet = new HashSet<>();
        List<Field> fieldList = Arrays.asList(fields);
        Collections.addAll(fieldSet, excludeFields);
        for (Field field : fields) {
            if (fieldSet.contains(field.getName())) {
                fieldList.remove(field);
            }
        }
        return fieldList.toArray(new Field[0]);
    }
}
