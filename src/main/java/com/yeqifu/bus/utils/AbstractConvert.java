package com.yeqifu.bus.utils;

import com.alibaba.fastjson.JSONObject;
import com.yeqifu.bus.enums.Function;

public class AbstractConvert {

    private static String tag = ".";

    public String getValue(Object from, Rule rule) {
        return "";
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
        //2a6d8780f9acba62208066cc8bb5dc30d64ccdba
        JSONObject obj = new JSONObject();
        for (String s : value) {
            obj = fromJson.getJSONObject(s);
            key = s;
        }
        return obj.getString(key);
    }
}
