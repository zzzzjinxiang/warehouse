package com.yeqifu.bus.utils;

import com.yeqifu.bus.entity.PropertiesInfo;

import java.io.*;
import java.util.*;

public class LoadPropertyBasicInfoUtil {
    Map<String, PropertiesInfo> propMap;

    public Map<String, PropertiesInfo> getPropMap() {
        return propMap;
    }

    public void setPropMap(Map<String, PropertiesInfo> propMap) {
        this.propMap = propMap;
    }

    public void loadFilePath(String path) {
        propMap = new HashMap<>();
        loadFilePath(new File(path));
    }

    public void loadFilePath(File file) {
        PropertiesInfo pi = new PropertiesInfo();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                loadFilePath(f);
            }
        } else {
            if (file.getName().endsWith(pi.getSuffix())) {
                pi.setFile(file);
                pi.setPath(file.getAbsolutePath());
                propMap.put(file.getAbsolutePath(), pi);
            }
        }
    }

    public static Properties loadProperties(File file) throws IOException {
        Properties properties = new Properties();
        properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
        return properties;
    }
}
