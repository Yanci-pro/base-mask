package com.yanci.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Yanci
 * @date 2022/6/18  12:01
 * Description
 */
public class ResourcesUtil {
    public static String getProperties(String name) {
        Properties props = getPropertiesInCache();
        String value = props.getProperty(name);
        return value;
    }

    public static Properties getPropertiesInCache() throws SecurityException {
        Properties pro = null;
        if (pro == null) {
            pro = getProperties();
        }
        return pro;
    }

    private static Properties getProperties() {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResource("encry.properties").openStream();
            Properties props = new Properties();
            props.load(in);
            in.close(); // 别忘了关流
            return props;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
