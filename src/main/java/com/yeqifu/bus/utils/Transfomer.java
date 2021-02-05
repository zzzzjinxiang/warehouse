package com.yeqifu.bus.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Transfomer extends AbstractConvert{

    public void transformByName(String fileName, String mode, Object from, Object to) {
        List<Rule> list;
        list = loadProperties(fileName, mode);
    }

    public List<Rule> loadProperties(String fileName, String mode) {
        LoadProperties loader = new LoadProperties();
        return loadProperties(loader.loadProperties(fileName+"-"+mode));
    }

    public List<Rule> loadProperties(Properties prop) {
        List<Rule> list = new ArrayList<>();
        for(Object rule:prop.values()) {
            Rule item = new Rule(rule.toString());
            list.add(item);
        }
        return list;
    }

    public void parseByRules(String fileName, List<Rule> rules, Object from, Object to) {

    }

    /**
     * 遍历rules,初始化to对象
     * @param rules
     * @param from
     * @param to
     */
    public void transfomer(List<Rule> rules, Object from, Object to) {
        for(Rule rule:rules) {
            transform(rule, from, to);
        }
    }

    /**
     * 1.获取from中规则的值
     * 2.将from中的值，传入to中
     * @param rule
     * @param from
     * @param to
     */
    public void transform(Rule rule, Object from, Object to) {
        String value = getValueByRuleFrom(rule, from);
        setValueByRuleTo(value, rule, to);
    }

    private void setValueByRuleTo(String value, Rule rule, Object to) {

    }

    private String getValueByRuleFrom(Rule rule, Object from) {
        String value;
        if(from instanceof JSONObject) {
            value = getValue((JSONObject) from, rule);
        } else {
            value = getValue(from, rule);
        }
        return value;
    }


}
