package com.znsd.instapundit.cache.tactics;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class CacheTactics {

    // 视频缓存策略
    public static final String VIDEO_PREFIX = "VideoBean*";
    public static final long VIDEO_EXPIRE = 60L;

    public static final String MENU_PREFIX = "MenuBean*";
    public static final long MENU_EXPIRE = 600L;

    public static String getKey(String prefix, String className, String methodName, Object param) {
        StringBuffer sb = new StringBuffer();
        sb.append(prefix).append(className).append(methodName);
        Field[] fields = param.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) { //设置是否允许访问，不是修改原来的访问权限修饰词。
            fields[i].setAccessible(true);
            Object value = null;
            try {
                value = fields[i].get(param);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (!StringUtils.isEmpty(value)) {
                sb.append(fields[i].getName()).append(":").append(value);
            }
        }
        return sb.toString();
    }

}
