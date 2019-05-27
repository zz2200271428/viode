package com.znsd.instapundit.bxwmanage.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Controller
public class PictureController {
	//private static final String IMAGE_SERVER_URL = "/upload/img/";
	@Value("${xcloud.uploadPath}")
	private   String IMAGE_SERVER_URL;
//	@RequestMapping(value = "/fileUpload2",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  fileUpload2(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		String tuozhan = file.getOriginalFilename();
		String extName = tuozhan.substring(tuozhan.lastIndexOf("."));
		String uploadPath = request.getSession().getServletContext().getRealPath("/") +IMAGE_SERVER_URL;
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String day = simpleDateFormat.format(date);
		uploadPath+="/"+day+"/";
		// 如果上传文件目录不存在则创建
		File files = new File(uploadPath);
		if (!files.exists()) {
			files.mkdirs();
		}

		// 获得一个唯一的文件名
		String fileName = UUID.randomUUID().toString() + extName; // 把唯一的名字和拓展名拼接
		String filePath = uploadPath + fileName;// 把地址和文件名拼接得到图片路径

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filePath));
			fos.write(file.getBytes());// 写入
			fos.flush();
			fos.close();
		/*	String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();*//*
			String url = serverPath + request.getContextPath() + IMAGE_SERVER_URL +"/"+day+"/"+ fileName;*/
			String url = IMAGE_SERVER_URL +"/"+day+"/"+ fileName;
			//System.out.println("图片地址："+url);
			result.put("code", 0);
			result.put("msg", "");
			result.put("url",url);

		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", "图片上传失败!");
			result.put("url", "");
		}

		return result;
	}
}