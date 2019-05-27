/**
 *
 */
package file.service.web.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import file.service.common.FilePathUtil;
import file.service.web.exception.FileServiceException;
import file.service.web.handler.HandlerParameter;
import file.service.web.util.ImageProcessUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 图片缩放处理者
 * @author wen578351314@gmail.com
 * @date 2016年8月24日
 * @since 0.0.1-SNAPSHOT
 */
public class ImageRatioZoomHnadler extends AbstractImageHandler {

    private Log logger = LogFactory.getLog(getClass());


    /* (non-Javadoc)
     * @see com.life.file.service.web.handle.AbstractFileHandler#doHandler(com.life.file.service.web.handle.HandlerParameter)
     */
    @Override
    protected void doHandler(HandlerParameter handlerParameter) throws FileServiceException {
        PrintWriter out = null;
        try {
            out = handlerParameter.getResponse().getWriter();
            HttpServletRequest request = handlerParameter.getRequest();
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();
            diskFactory.setSizeThreshold(4 * 1024); // 硬盘缓存 1M
            // diskFactory.setRepository(new File(tempPath));
            ServletFileUpload upload = new ServletFileUpload(diskFactory); // 临时文件目录
            upload.setSizeMax(4 * 1024 * 1024); // 设置允许上传的最大文件大小 4M

            List<FileItem> fileItems = upload.parseRequest(request); // 解析HTTP请求消息头
            Iterator<FileItem> iter = fileItems.iterator();
            JSONArray array = new JSONArray();
            while (iter.hasNext()) {
                FileItem fileItem = iter.next();
                file.service.common.FileItem fi = getFilePath(fileItem, request);
                String imageSavePath = fi.getAbsolutePath();

                logger.info("doHandler|imageSavePath=" + imageSavePath);
                Map<String, Integer> ratioMap = getRatioMap(fileItem);

                for (Entry<String, Integer> it : ratioMap.entrySet()) {
                    String imgName = it.getKey();
                    logger.info("doHandler|imgName=" + imgName);
                    ImageProcessUtil.ratioZoom2(fileItem.getInputStream(), new File(imageSavePath + imgName), it.getValue());

                    JSONObject fileObject = new JSONObject();
                    fileObject.put("fileName", imgName);
                    fileObject.put("filePath", fi.getFilePath());
                    fileObject.put("absolutePath", imageSavePath);
                    array.add(fileObject);
                }
            }
            out.println(array.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 初始化图片比例map
     * @param fileItem
     * @return
     */
    public Map<String, Integer> getRatioMap(FileItem fileItem) {
        String suffix = file.service.common.FileItem.getExtensionName(fileItem.getName());
        String filePrefix = System.currentTimeMillis() + "" + RandomUtils.nextInt(1000);
        Map<String, Integer> ratioMap = new TreeMap<String, Integer>();
        ratioMap.put(new StringBuffer(filePrefix).append("_").append(1).append(".").append(suffix).toString(), 200);
        ratioMap.put(new StringBuffer(filePrefix).append("_").append(2).append(".").append(suffix).toString(), 350);
        ratioMap.put(new StringBuffer(filePrefix).append("_").append(3).append(".").append(suffix).toString(), 0);
        return ratioMap;
    }

    /**
     * 获取图片保存路径
     * @param item
     * @param request
     * @return
     */
    public file.service.common.FileItem getFilePath(FileItem item, HttpServletRequest request) {
        file.service.common.FileItem fileItem = new file.service.common.FileItem();
        String imagePath = ObjectUtils.toString(request.getParameter("filePath"));
        String fileName = item.getName();
        logger.info("完整的文件名：" + fileName);
        int index = fileName.lastIndexOf("\\");
        fileName = fileName.substring(index + 1, fileName.length());

        String dir = "";
        if (StringUtils.isNotBlank(imagePath)) {
            dir = super.fileSavePath + imagePath;
            fileItem.setFilePath(imagePath);
        } else {
            imagePath = FilePathUtil.getImgDatePath();
            fileItem.setFilePath(imagePath);
            dir = super.fileSavePath + imagePath;
        }
        File path = new File(dir);
        if (!path.exists()) {
            try {
                FileUtils.forceMkdir(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileItem.setAbsolutePath(dir);
        return fileItem;
    }
}
