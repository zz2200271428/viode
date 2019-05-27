package file.service.common;

import file.service.common.client.FileClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUpload {

    public static Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> result = new HashMap<String, Object>();
        //校验是否有文件
        if (file.isEmpty()) {
            result.put("code", 1);
            result.put("msg", "图片上传失败!");
            result.put("url", "");
        }

        FileClient fileClient = new FileClient();
        FileItem fileItem = null;
        try {
            fileItem = new FileItem(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileItem = fileClient.uploadFile(System.currentTimeMillis(), fileItem);
        if (fileItem == null) {
            result.put("code", 1);
            result.put("msg", "图片上传失败!");
            result.put("url", "");
        } else {
            result.put("code", 0);
            result.put("msg", "图片上传成功");
            result.put("url", fileItem.getUrl());
        }
        return result;
    }
}
