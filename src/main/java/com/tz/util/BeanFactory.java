package com.tz.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    private static Properties prop;

    static {
        try {
            prop = new Properties();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("bean.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String beanName) {
        Object obj = null;
        Class<?> c;
        try {
            c = Class.forName(prop.getProperty(beanName));
            obj = c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
