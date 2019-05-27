package com.znsd.instapundit.bxwmanage.controller;

import file.service.common.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class UploadController {

    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        return FileUpload.uploadFile(file);
    }
}
