/**
 *
 */
package file.service.common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: PropertiesUtil
 * @Description: 获取属性文件工具类
 * @author wen578351314@gmail.com
 * @date 2015年12月8日
 * @version 0.0.1-SNAPSHOT
 */
public class PropertiesUtil {

    private static Map<String, Properties> propertyMap = null;

    /**
     * 获取属性值
     * @param file
     * @param key
     * @return
     */
    public static String getProperty(String file, String key) {
        if (propertyMap == null) {
            propertyMap = new ConcurrentHashMap<String, Properties>();
        }
        String fileName = getFileNameFromPath(file);
        if (!propertyMap.containsKey(fileName)) {
            Properties properties = new Properties();
            InputStream inStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(file);
            try {
                properties.load(inStream);
                propertyMap.put(fileName, properties);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("PropertiesUtil.getProperty properties file can not exists");
                return null;
            } finally {
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return properties.getProperty(key);
        }
        return propertyMap.get(fileName).getProperty(key);
    }

    /**
     * 根据文件路径获取文件名
     * @param path
     * @return
     */
    public static String getFileNameFromPath(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.getName();
        }

        int location = path.lastIndexOf("\\");
        if (location == -1) {
            return path;
        }
        return path.substring(location + 1);
    }

    /**
     * 获得以指定前缀开头的多个key的value
     *
     * @param keyPrefix
     * @return
     */
    public static Map<String, String> getPropertyWithPrefix(String file,
                                                            String keyPrefix) {
        if (propertyMap == null) {
            getProperty(file, "");
        }
        Set<Object> configKeys = propertyMap.get(file).keySet();
        Map<String, String> map = new HashMap<String, String>();
        if (configKeys != null && configKeys.size() > 0) {
            for (Object key : configKeys) {
                String k = key.toString();
                if (k.startsWith(keyPrefix)) {
                    map.put(k, getProperty(file, k));
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getProperty("file.properties", "uploadServer_0"));
    }

    public static int getPropertyAsInt(String file, String key) {
        String value = getProperty(file, key);
        if (StringUtils.isNotBlank(value)) {
            return NumberUtils.toInt(value);
        }
        return -1;
    }
}
