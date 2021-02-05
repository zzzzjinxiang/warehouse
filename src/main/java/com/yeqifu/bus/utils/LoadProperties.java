package com.yeqifu.bus.utils;

import java.util.Properties;

public class LoadProperties {

    static {
        PreLoadProperties.init();
    }

    public Properties loadProperties(String path) {
        return PreLoadProperties.propMap.get(path).getProperties();
    }
}
