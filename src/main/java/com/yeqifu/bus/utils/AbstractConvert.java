package com.yeqifu.bus.utils;

import com.alibaba.fastjson.JSONObject;
import com.yeqifu.bus.enums.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AbstractConvert {
    private static final String EMPTY = "空";
    private Map<String, Object> ori = new HashMap<>();
    private Map<String, Object> generat = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private static String tag = ".";

    public void reset() {
        ori = new HashMap<>();
        generat = new HashMap<>();


    }

    public String getValue(Object from, Rule rule) {
        return "";
    }

    public String getValue(Object from, String key) throws Exception {
        if (key.startsWith("#")) {
            String value = key.substring(1);
            if (EMPTY.equals(key)) {
                return "";
            } else {
                return value;
            }
        } else {
            String[] tags = key.split("[.]");
            Object another = from;
            String prefix = "";
            for (int index = 1; index < tags.length; index++) {
                prefix += tags[index];
                if (ori.containsKey(prefix)) {
                    // 缓存到map中加快访问
                    another = ori.get(prefix);
                } else {
                    if (JSONObject.class.isAssignableFrom(another.getClass())) {
                        StringBuffer buffer = new StringBuffer();
                        for (int cnt = index; cnt < tags.length; cnt++) {
                            buffer.append(tags[cnt] + ".");
                        }
                    } else {
                        Field field;
                        try {
                            // 从缓存中获取
                            field = CacheReflectUtil.getField(another.getClass(), prefix);
                        } catch (NoSuchFieldException e) {
                            // 打个log，抛异常
                            logger.error("", e);
                            throw e;
                        }
                        Class<?> clazz = field.getType();
                        String methodName;
                        if (boolean.class.isAssignableFrom(clazz)) {
//                            methodName
                            methodName = generateIsMethodName(tags[index]);
                        } else {
                            methodName = generateGetMethodName(tags[index]);
                        }
                        Method method;
                        try{
                            another = method.invoke(another, (Object[]) null);
                        }
                    }
                }
            }
        }
    }

    private String generateGetMethodName(String tag) {
    }

    private String generateIsMethodName(String tag) {
    }

    public String getValue(JSONObject fromJson, Rule rule) {
        String key = rule.getFrom();
        if (key.startsWith("!")) {
            return getValueFromJson(fromJson, rule, Function.getFunction(key));
        } else {
            return getValueFromJson(fromJson, rule);
        }
    }

    public String getValueFromJson(JSONObject fromJson, Rule rule, Function function) {
        String from = rule.getFrom();
        String method = from;
        return from;
    }

    public String getValueFromJson(JSONObject fromJson, Rule rule) {
        String from = rule.getFrom();
        String[] value = from.split(tag);
        // 长度为0，则返回空值
        if (value.length == 0) {
            return "";
        }
        // #开头处理值
        String key = "";
        if (from.startsWith("#")) {
            String v = from.substring(1);
            if ("空".equals(from)) {
                return "";
            } else {
                return v;
            }
        }
        // 处理json串
        JSONObject obj = new JSONObject();
        for (String s : value) {
            obj = fromJson.getJSONObject(s);
            key = s;
        }
        return obj.getString(key);
    }
}
