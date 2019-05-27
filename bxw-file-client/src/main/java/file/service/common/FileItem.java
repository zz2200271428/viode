package file.service.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author wen578351314@gmail.com
 * @version 0.0.1-SNAPSHOT
 * @Title: FileItem
 * @Description: 文件实体类
 * @date 2015年12月8日
 */
public class FileItem implements Serializable {

    /**
     * 支持的图片格式
     */
    private static String[] SUPPORT_FORMAT = {"jpg", "png", "gif", "bmp"};

    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件输入流
     */
    private InputStream fileStream;

    /**
     * 文件相对路径
     */
    private String filePath;

    /**
     * 文件绝对路径
     */
    private String absolutePath;

    private String url; // 图片访问url

    public FileItem() {

    }

    /**
     * @param fileName
     */
    public FileItem(String fileName) {
        super();
        this.fileName = fileName;
    }

    /**
     * @param fileName
     * @param fileStream
     */
    public FileItem(String fileName, InputStream fileStream) {
        super();
        this.fileName = fileName;
        this.fileStream = fileStream;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileStream
     */
    public InputStream getFileStream() {
        return fileStream;
    }

    /**
     * @param fileStream the fileStream to set
     */
    public void setFileStream(InputStream fileStream) {
        this.fileStream = fileStream;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the absolutePath
     */
    public String getAbsolutePath() {
        return absolutePath;
    }

    /**
     * @param absolutePath the absolutePath to set
     */
    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    /**
     * 获取文件扩张名
     *
     * @return
     */
    public String getExtensionName() {
        return getExtensionName(this.fileName);
    }

    /**
     * 更具文件名获取文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 判断文件格式是否支持上传
     *
     * @param filename
     * @return
     */
    public boolean isSupport() {
        return Arrays.asList(SUPPORT_FORMAT).contains(getExtensionName());
    }

    /**
     * 根据文件编号
     *
     * @return
     */
    public int getNo() {
        byte[] md5 = DigestUtils.md5(String.valueOf(this.fileName));
        int i = md5[0] + md5[1] << 8;
        return Math.abs(i);
    }

    /**
     * 根据响应结果组装文件路径
     *
     * @param pathStr
     * @param fileItems
     * @return
     */
    public static List<FileItem> assembleFilePath(long id, String pathStr, List<FileItem> fileItems) {
        if (pathStr == null || StringUtils.isBlank(pathStr)) {
            return fileItems;
        }
        String[] paths = pathStr.split(";");
        FileItem fileItem = null;
        for (int i = 0; i < paths.length; i++) {
            fileItem = fileItems.get(i);
            fileItem.setFilePath(paths[i]);
            fileItem.setAbsolutePath(FilePathUtil.getImagesDomain(id) + "/" + fileItem.getFilePath());
            fileItem.setUrl(FilePathUtil.getImagesDomain(id) + "/" + fileItem.getFilePath());
        }
        return fileItems;
    }

    /**
     * 设置图片的访问url
     *
     * @param fileItems
     * @return
     */
    public static List<FileItem> setUrl(long id, List<FileItem> fileItems) {
        if (fileItems == null || fileItems.size() <= 0) {
            return fileItems;
        }

        for (FileItem fileItem : fileItems) {
            fileItem.setUrl(new StringBuilder().append(FilePathUtil.getImagesDomain(id))
                    .append("/")
                    .append(fileItem.getFilePath())
                    .append("/")
                    .append(fileItem.getFileName()).toString());
        }
        return fileItems;
    }

    @Override
    public String toString() {
        return "FileItem [fileName=" + fileName + ", fileStream=" + fileStream + ", filePath=" + filePath
                + ", absolutePath=" + absolutePath + ", url=" + url + "]";
    }
}