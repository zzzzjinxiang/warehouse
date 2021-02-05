package com.yeqifu.bus.utils;

public class Rule {
    private String name;
    private String from;
    private String to;
    private String type;
    private String minLen;
    private String maxLen;
    private String value;
    private boolean require;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMinLen() {
        return minLen;
    }

    public void setMinLen(String minLen) {
        this.minLen = minLen;
    }

    public String getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(String maxLen) {
        this.maxLen = maxLen;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getRequire() {
        return require;
    }

    public void setRequire(boolean require) {
        this.require = require;
    }

    public Rule(String rule) {
        String[] rules = rule.split(";");
        this.name = rules[0];
        this.from = rules[1];
        this.to = rules[2];
        this.type = rules[3];
        this.minLen = rules[4];
        this.maxLen = rules[5];
        if ("Y".equals(rules[6])) {
            this.require = true;
        }
        if (rules.length == 8) {
            if ("空".equals(rules[7])) {
                this.value = "";
            } else {
                this.value = rules[7].substring(1);
            }
        } else {
            this.value = "";
        }

    }
}
