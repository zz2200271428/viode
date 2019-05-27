/**
 *
 */
package file.service.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author wen578351314@gmail.com
 * @version 0.0.1-SNAPSHOT
 * @Title: FilePathUtil
 * @Description: 文件路径工具类
 * @date 2015年12月8日
 */
public class FilePathUtil {

    private static final String FILE_SERVICE_CONFIG = "file.properties";
    private static final Map<String, String> SERVERS = PropertiesUtil.getPropertyWithPrefix(FILE_SERVICE_CONFIG, "uploadServer_");

    /**
     * 获取文件服务器域名地址
     *
     * @return
     */
    public static String getImagesDomain(long id) {
        //return PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "images_domain");
        String domain = PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "images_domain");
        long node = id % SERVERS.size();
        return MessageFormat.format(domain, node);
    }

    /**
     * 获取文件保存路径
     *
     * @return
     */
    public static String getFileSavePath() {
        return "";
    }

    /**
     * 获取图片上传保存的路径
     *
     * @return
     */
    public static String getImgDatePath() {
        return getPublicSavePath(System.currentTimeMillis());
    }

    /**
     * 根据用户id获取图片保存路径
     *
     * @return
     */
    public static String getPathByUserId(long userId) {
        return new StringBuilder()
                .append(PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "protected_save_path"))
                .append("/")
                .append(getUserFilePrefixPath(userId))
                .append("/")
                .append(getUserBasicDir(userId) % 400 + 1)
                .append("/")
                .append(userId)
                .append("/").toString();
    }

    /**
     * 生成带日期的路径
     * @param date
     * @return
     */
    public static String getTimePath(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 获取公开的文件存储路径
     *
     * @param id
     * @return
     */
    public static String getPublicSavePath(long id, Date date) {
        return new StringBuilder()
                //.append(PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "public_save_path"))
                .append("/")
                .append(getUserFilePrefixPath(id))
                .append("/")
                .append(getUserBasicDir(id) % 400 + 1)
                .append("/")
                .append(getTimePath(date))
                .append("/").toString();
    }

    /**
     * 获取公开文件保存路径
     * @param id
     * @return
     */
    public static String getPublicSavePath(long id) {
        return getPublicSavePath(id, new Date());
    }

    /**
     * 根据指定的id获取文件目录的前缀
     *
     * @param id
     * @return
     */
    public static String getUserFilePrefixPath(long id) {
        Map<String, String> servers = PropertiesUtil.getPropertyWithPrefix(FILE_SERVICE_CONFIG, "uploadServer_");
        if (servers == null) {
            return null;
        }
        return "s_" + id % SERVERS.size();
    }

    /**
     * 根据指定的id获取对象的目录
     *
     * @param userId
     * @return
     */
    private static int getUserBasicDir(Long userId) {
        byte[] md5 = DigestUtils.md5(String.valueOf(userId));
        int i = md5[0] + md5[1] << 8;
        return Math.abs(i);
    }

    /**
     * 生成文件名称
     *
     * @param filePrefix
     * @param fileSuffix
     * @return
     */
    public static String getFileName(String filePrefix, String fileSuffix) {
        return filePrefix + System.currentTimeMillis() + RandomUtils.nextInt(1000) + "." + fileSuffix;
    }

    /**
     * 根据原始文件名生成新的文件
     * @return
     */
    public static String getFileNameByOriginName(String fileName) {
        byte[] md5 = DigestUtils.md5(fileName);
        int i = md5[0] + md5[1] << 8;
        String str = String.valueOf(Math.abs(i));
        String md5Str = DigestUtils.md5Hex(str);
        return StringUtils.substring(md5Str, md5Str.length() / 2) + "." + FileItem.getExtensionName(fileName);
    }

    /**
     * 根据id获取上传服务器节点
     *
     * @param id
     * @return
     */
    public static String getServer(long id) {
        return PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "uploadServer_" + id % SERVERS.size());
    }

    public static void main(String[] args) {
        System.out.println(getPublicSavePath(1));
        System.out.println(getPathByUserId(10019));
        System.out.println(getImagesDomain(10019));
        System.out.println(getServer(10019));
        System.out.println(getFileNameByOriginName("123.jpg"));
    }
}
