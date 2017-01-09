package com.tz.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Description:
 * Created by xhj224.
 * Date: 2016/12/15 17:22.
 * Project: crm1215.
 */
public class MyUtil {
    public static String toUTF8(String value) {
        String newValue = "";
        if (value != null && value.length() != 0) {
            try {
                newValue = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return newValue;
    }
}
