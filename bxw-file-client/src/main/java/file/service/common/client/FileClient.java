/**
 *
 */
package file.service.common.client;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import file.service.common.FileItem;
import file.service.common.FilePathUtil;
import file.service.common.PropertiesUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author wen578351314@gmail.com
 * @version 0.0.1-SNAPSHOT
 * @Title: FileServiceClient
 * @Description: 文件服务客户端，用来获取、上传文件
 * @date 2015年12月8日
 */
public class FileClient {

    private static final String CMD = "cmd";
    private static final String REQ_ACTION = "req_action";
    private static final String FILE_SERVICE_CONFIG = "file.properties";
    private static final String FILE_SERVICE_ACTION = PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "file_service_action");
    private static final String FILE_ROOT_PATH = PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "file_root_path");
    private static final String PROTECTED_SAVE_PATH = PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "protected_save_path");
    private static final String PUBLIC_SAVE_PATH = PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "public_save_path");
    public static final int HTTP_CONN_TIMEOUT = PropertiesUtil.getPropertyAsInt(FILE_SERVICE_CONFIG, "http_conn_timeout");
    public static final String HTTP_REQUEST_CHARSET = PropertiesUtil.getProperty(FILE_SERVICE_CONFIG, "http_request_charset");

    private ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<Map<String, String>>();

    /**
     * 非用户级别的文件上传
     *
     * @param fileItem
     * @return
     */
    public FileItem uploadFile(FileItem fileItem) {
        return this.uploadFile(Lists.newArrayList(fileItem)).get(0);
    }

    /**
     * 非用户级别多文件上传
     *
     * @param fileItems
     * @return
     */
    public List<FileItem> uploadFile(List<FileItem> fileItems) {
        List<FileItem> list = new ArrayList<FileItem>();
        for (FileItem fileItem : fileItems) {
            fileItem = this.uploadFile(fileItem.getNo(), fileItems, FilePathUtil.getPublicSavePath(Long.valueOf(fileItem.getNo() + "")), PUBLIC_SAVE_PATH).get(0);
            list.add(fileItem);
        }
        return list;
    }

    /**
     * 用户级别的单文件上传:用户级别标识可直接通过用户id获取文件流
     *
     * @param userId
     * @param fileItem
     * @return
     */
    public FileItem uploadFile(long userId, FileItem fileItem) {
        return this.uploadFile(userId, Lists.newArrayList(fileItem)).get(0);
    }

    /**
     * 用户级别的多文件上传
     *
     * @param userId
     * @param fileItems
     * @return
     */
    public List<FileItem> uploadFile(long userId, List<FileItem> fileItems) {
        return this.uploadFile(userId, fileItems, FilePathUtil.getPathByUserId(userId), "");
    }

    /**
     * 文件上传
     *
     * @param id
     * @param fileItems
     * @param filePath
     * @return
     */
    public List<FileItem> uploadFile(long id, List<FileItem> fileItems, String filePath, String authPath) {
        String serverNode = FilePathUtil.getServer(id);
        String serverAction = serverNode + FILE_SERVICE_ACTION;
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put(REQ_ACTION, serverAction);
        reqMap.put(CMD, "save");
        reqMap.put("rootPath", FILE_ROOT_PATH); // 文件保存的根目录
        reqMap.put("authPath", authPath);
        reqMap.put("filePath", filePath); // 文件保存位置
        threadLocal.set(reqMap);
        String responseStr = sendReq(getUploadRequestURL(HTTP_REQUEST_CHARSET), fileItems);
        List<FileItem> fileItemList = FileItem.assembleFilePath(id, responseStr, fileItems);
        return fileItemList;
    }

    /**
     * 图片缩放比例
     *
     * @param id
     * @param fileItems
     * @return
     */
    public List<FileItem> imageRatioZoom(long id, List<FileItem> fileItems) {
        String serverNode = FilePathUtil.getServer(id);
        String serverAction = serverNode + FILE_SERVICE_ACTION;
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put(REQ_ACTION, serverAction);
        reqMap.put(CMD, "ratioZoom");
        reqMap.put("rootPath", FILE_ROOT_PATH);
        threadLocal.set(reqMap);
        String responseStr = sendReq(getUploadRequestURL(HTTP_REQUEST_CHARSET), fileItems);
        if (StringUtils.isNotEmpty(responseStr)) {
            List<FileItem> fileItemList = JSONArray.parseArray(responseStr, FileItem.class);
            return FileItem.setUrl(id, fileItemList);
        }
        return fileItems;
    }

    /**
     * 获取文件上传的地址
     *
     * @param charset
     * @return
     */
    private String getUploadRequestURL(String charset) {
        Map<String, String> reqMap = this.threadLocal.get();
        if (reqMap == null) {
            return "";
        }
        String requestAction = reqMap.remove(REQ_ACTION);

        StringBuilder reqStr = new StringBuilder(requestAction).append("?");
        Set<Entry<String, String>> set = reqMap.entrySet();
        for (Entry<String, String> entry : set) {
            try {
                reqStr.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), charset)).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return reqStr.substring(0, reqStr.length() - 1);
    }

    /**
     * 发送普通的http请求
     *
     * @param urlStr
     * @return
     */
    public String sendReq(String urlStr) {
        System.out.println("urlStr=" + urlStr);
        BufferedReader bufferread = null;
        InputStreamReader isr = null;
        HttpURLConnection con = null;
        try {

            final URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            // con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/octet-stream");
            con.setConnectTimeout(HTTP_CONN_TIMEOUT);
            con.setReadTimeout(30000);
            isr = new InputStreamReader(con.getInputStream());
            bufferread = new BufferedReader(isr);
            StringBuffer statusStr = new StringBuffer(512);
            String str = "";
            while ((str = bufferread.readLine()) != null) {
                statusStr.append(str);
            }

            if (statusStr.toString().equals("0")) {
                return null;
            }
            return statusStr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(bufferread);
            IOUtils.closeQuietly(isr);
            if (con != null) {
                con.disconnect();
            }
        }
    }

    /**
     * 发送文件上传的请求
     *
     * @param urlStr
     * @param fileItems
     * @return
     */
    private String sendReq(String urlStr, List<FileItem> fileItems) {
        OutputStream out = null;
        try {
            String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(HTTP_CONN_TIMEOUT);
            conn.setReadTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            out = new DataOutputStream(conn.getOutputStream());
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
            int leng = fileItems.size();
            for (int i = 0; i < leng; i++) {
                FileItem fileItem = fileItems.get(i);
                StringBuilder sb = new StringBuilder();
                sb.append("--");
                sb.append(BOUNDARY);
                sb.append("\r\n");
                sb.append("Content-Disposition: form-data;name=\"file" + i + "\";filename=\"" + fileItem.getFileName() + "\"\r\n");
                sb.append("Content-Type:application/octet-stream\r\n\r\n");

                byte[] data = sb.toString().getBytes();
                out.write(data);
                DataInputStream in = new DataInputStream(fileItem.getFileStream());
                int bytes = 0;
                byte[] bufferOut = new byte[1024];
                while ((bytes = in.read(bufferOut)) != -1) {
                    out.write(bufferOut, 0, bytes);
                }
                out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
                in.close();
            }
            out.write(end_data);
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer statusStr = new StringBuffer(512);
            String str = "";
            while ((str = reader.readLine()) != null) {
                statusStr.append(str);
            }

            if (statusStr.toString().equals("0")) {
                return null;
            }
            return statusStr.toString();
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
