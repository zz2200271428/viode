package com.znsd.instapundit.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Appfileupload {
    public String saveFile(MultipartFile file) throws IOException {
        //判断文件是否为空
        if (file.isEmpty()) {
            return null;
        }
        // 获取文件名称
        String filename = file.getOriginalFilename();
        // 截取文件后缀
        String fileext = filename.substring(filename.lastIndexOf("."));
        // 生成新的随机文件名称
        String newfileName = UUID.randomUUID() + fileext;
        // 文件保存路径
        File savePath = new File("E:/fileUpload/" + newfileName);
        //判断文件父目录是否存在
        if (!savePath.getParentFile().exists()) {
            savePath.getParentFile().mkdir();
        }
        // 上传文件
        file.transferTo(savePath);
        return newfileName;
    }

}
