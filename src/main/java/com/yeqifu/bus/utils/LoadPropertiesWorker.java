package com.yeqifu.bus.utils;

import com.yeqifu.bus.entity.PropertiesInfo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class LoadPropertiesWorker extends Thread {
    Map<String, PropertiesInfo> propertiesInfoMap;
    boolean exitFlag;

    public Map<String, PropertiesInfo> getPropertiesInfoMap() {
        return propertiesInfoMap;
    }

    public void setPropertiesInfoMap(Map<String, PropertiesInfo> propertiesInfoMap) {
        this.propertiesInfoMap = propertiesInfoMap;
    }

    public void init() {
        this.exitFlag = true;
        (new LoadPropertiesWorker()).start();
    }

    @Override
    public void run() {
        while (exitFlag) {
            load();
        }
    }

    public void load() {
        if (propertiesInfoMap.equals(null)) {
            this.exit();
        }
        Iterator propertiesInfo = propertiesInfoMap.entrySet().iterator();
        while (propertiesInfo.hasNext()) {
            Map.Entry<String, PropertiesInfo> entry = (Map.Entry) propertiesInfo.next();
            PropertiesInfo pi = entry.getValue();
            try {
                Properties properties = LoadPropertyBasicInfoUtil.loadProperties(new File(pi.getPath()));
                pi.setProperties(properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit() {
        this.exitFlag = false;
    }
}
