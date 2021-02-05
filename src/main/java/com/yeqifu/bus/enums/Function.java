package com.yeqifu.bus.enums;

public enum Function {
    ;
    private String function;

    public String getFunction() {
        return function;
    }

    public static Function getFunction(String function) {
        for(Function obj:Function.values()) {
            if(obj.getFunction().equals(function)) {
                return obj;
            }
        }
        return null;
    }
}
