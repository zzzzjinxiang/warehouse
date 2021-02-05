package com.yeqifu.bus.utils;

import com.yeqifu.bus.entity.PropertiesInfo;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PreLoadProperties {
    static LoadPropertyBasicInfoUtil loader;
    static LoadPropertiesWorker worker;
    static Map<String, PropertiesInfo> propMap;

    public void loadProperties(String path) throws IOException {
        PropertiesInfo propertiesInfo = new PropertiesInfo();
        propertiesInfo.setPath(path);

        Properties p = propertiesInfo.getProperties();
        FileInputStream in = new FileInputStream(path);
        p.load(in);
    }

    public static void init() {
        String path = "";
        try {
            path = getPath(LoadPropertyBasicInfoUtil.class, "cache");
        } catch (Exception e) {

        }
        loader = new LoadPropertyBasicInfoUtil();
        // 加载文件路径
        loader.loadFilePath(path);
        worker.setPropertiesInfoMap(loader.getPropMap());
        //加载对应属性
        worker.init();
        propMap = worker.propertiesInfoMap;
    }

    public static String getPath(Class<?> clazz, String dir) throws URISyntaxException {
        try {
            return (new File(clazz.getClassLoader().getResource("META-INF/" + dir).toURI()).getAbsolutePath());
        }catch (Exception e){

        }
        return "";
    }

    // 此方法可以包装到一个thread中
    public Properties getProperties(File file) throws Exception {
        Properties properties = new Properties();
        properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
        return properties;
    }

}
