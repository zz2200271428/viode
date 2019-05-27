/**
 *
 */
package file.service.web.handler.impl;

import file.service.common.FilePathUtil;
import file.service.web.exception.FileServiceException;
import file.service.web.handler.HandlerParameter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * 文件上传处理类
 *
 * @author wen578351314@gmail.com
 * @date 2015年12月8日
 * @since 1.0.0-SNAPSHOT
 */
public class FileUploadHandler extends AbstractImageHandler {

    private Log logger = LogFactory.getLog(getClass());

    /**
     * 文件上传
     *
     * @throws IOException
     */
    public void doHandler(HandlerParameter handlerParameter) throws FileServiceException {
        HttpServletRequest request = handlerParameter.getRequest();
        HttpServletResponse response = handlerParameter.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();
            // 硬盘缓存 1M
            diskFactory.setSizeThreshold(1000 * 1024 * 1024);
            // 临时文件目录
            // diskFactory.setRepository(new File(tempPath));
            ServletFileUpload upload = new ServletFileUpload(diskFactory);
            // 设置允许上传的最大文件大小 4M
            upload.setSizeMax(1000 * 1024 * 1024);
            // 解析HTTP请求消息头
            List<FileItem> fileItems = upload.parseRequest(request);
            Iterator<FileItem> iter = fileItems.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                uploadFile(item, pw, request);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            pw.println("文件上传异常" + e.getMessage());
            return;
        } catch (IOException e) {
            e.printStackTrace();
            pw.println("IO异常读取异常" + e.getMessage());
            return;
        } catch (Exception e) {
            e.printStackTrace();
            pw.println(e.getMessage());
            return;
        } finally {
            IOUtils.closeQuietly(pw);
        }
    }

    /**
     * 处理文件上传
     *
     * @param item
     * @param out
     * @param request
     * @throws Exception
     */
    private void uploadFile(FileItem item, PrintWriter out, HttpServletRequest request) throws Exception {
        if (item == null) {
            return;
        }

        String authPath = ObjectUtils.toString(request.getParameter("authPath")); // 用户级别文件路劲
        String filePath = ObjectUtils.toString(request.getParameter("filePath")); // 生成的文件路径
        String rootPath = ObjectUtils.toString(request.getParameter("rootPath")); // 文件保存的根目录
        String fileName = item.getName();
        logger.info("完整的文件名：" + fileName);
        int index = fileName.lastIndexOf("\\");
        fileName = fileName.substring(index + 1, fileName.length());
        // 判断支持的文件格式
        String extensionName = file.service.common.FileItem.getExtensionName(fileName);

        if (StringUtils.isBlank(filePath)) {
            logger.error("uploadFile|msg|文件保存路径未指定");
            return;
        }
        // 如果保存路径不存在则创建
        File path = new File(rootPath + File.separator + authPath + File.separator + filePath);
        if (!path.exists()) {
            FileUtils.forceMkdir(path);
        }

        // 如果文件已经存在就直接返回原来的文件
        File uploadFile = new File(path + File.separator + FilePathUtil.getFileNameByOriginName(fileName));
        /*
         * if (uploadFile.exists()) { out.println((filePath + File.separator +
         * uploadFile.getName() + ";")); return; }
         */

        /*
         * if (StringUtils.isBlank(fileName)) { fileName =
         * FilePathUtil.getFileName("p_", extensionName); }
         */

        try {
            item.write(uploadFile);
            out.println((filePath + File.separator + uploadFile.getName() + ";"));

            logger.info("uploadFile|文件上传成功|imagePath|" + filePath + "|fileName|" + uploadFile.getName());
        } catch (Exception e) {
            logger.error("uploadFile|文件上传异常|error|" + e.getMessage());
            e.printStackTrace();
        }
    }
}
