package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.CommentBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.bxwapp.config.resolver.UserIDArguments;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.AppUserService;
import com.znsd.instapundit.service.CommentService;
import com.znsd.instapundit.service.SpecialColumnService;
import com.znsd.instapundit.service.VideoService;
import file.service.common.FileItem;
import file.service.common.client.FileClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/video")
@Controller
public class VideoController {

    @Reference
    private VideoService videoService;

    @Reference
    private AppUserService appUserService;

    @Reference
    private CommentService commentService;

    @Reference
    private SpecialColumnService specialColumnService;

    @RequestMapping("/play")
    public String play(@RequestParam(value = "videoID") Integer videoID, Model model) {
        VideoBean video = videoService.getVideoByID(videoID);
        model.addAttribute("video", video);
        Integer columnID = video.getFrom_sc();
        if (columnID != 0) { // 如果是专栏查询出专栏下的所有视频列表
            model.addAttribute("isColumn", true);
            List<VideoBean> columnVideos = videoService.getVideosBySpeID(columnID);
            model.addAttribute("columnVideos", columnVideos);
        } else {
            model.addAttribute("isMember", false);
        }
        boolean isCollect = videoService.isCollect(videoID, AppUserController.USER_ID);
        model.addAttribute("isCollect", isCollect);
        boolean isMember = appUserService.isMember(AppUserController.USER_ID);
        model.addAttribute("isMember", isMember);
        List<CommentBean> comments = commentService.getComments(videoID);
        model.addAttribute("comments", comments);
        return "play";
    }

    @PostMapping("/comment")
    @ResponseBody
    public Result comment(@UserIDArguments Integer userID, @RequestParam("videoID") Integer videoID, @RequestParam("comment") String comment, Model model) {
        Result result = commentService.insertComment(userID, videoID, comment);
        return result;
    }

    @GetMapping("/comments")
    @ResponseBody
    public List<CommentBean> comments(@RequestParam("videoID") Integer videoID) {
        List<CommentBean> comments = commentService.getComments(videoID);
        return comments;
    }

    @PostMapping("/collect")
    @ResponseBody
    public boolean collect(@UserIDArguments Integer userID, @RequestParam("videoID") Integer videoID, @RequestParam("flag") boolean flag) {
        return videoService.collect(videoID, userID, flag);
    }

    @PostMapping("/pay")
    @ResponseBody
    public Result pay(@UserIDArguments Integer userID, @RequestParam("videoID") Integer videoID) {
        Result result = videoService.pay(videoID, userID);
        return result;
    }


    @GetMapping("/isPay")
    @ResponseBody
    public boolean isPay(@UserIDArguments Integer userID, @RequestParam("videoID") Integer videoID) {
        return videoService.isPay(videoID, userID);
    }

    @GetMapping("/to_upload")
    public String to_upload(HttpSession session) {
        session.setAttribute("to_upload_token", UUID.randomUUID().toString());
        return "video-upload";
    }

    @GetMapping("/to_create_column")
    public String to_create_column(HttpSession session) {
        session.setAttribute("to_create_column_token", UUID.randomUUID().toString());
        return "create-column";
    }

    @GetMapping("/mycolumn")
    @ResponseBody
    public List<SpecialColumnBean> mycolumn(@UserIDArguments Integer userID) {
        return specialColumnService.mycolumn(userID);
    }

    @PostMapping("/column-form")
    public String column_form(HttpSession session, @RequestParam("to_create_column_token") String clientToken, @UserIDArguments Integer userID, SpecialColumnBean column, @RequestParam("file") MultipartFile file, Model model) {
        String serverToken = (String) session.getAttribute("to_create_column_token");
        if (serverToken == null) { // 重复提交了
            return "create-column";
        }
        if (!serverToken.equals(clientToken)) { // 重复提交了
            return "create-column";
        }
        // 提交了就移除session中的token令牌
        session.removeAttribute("to_create_column_token");
        FileClient fileClient = new FileClient();
        FileItem fileItem = null;

        try {
            fileItem = new FileItem(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileItem = fileClient.uploadFile(System.currentTimeMillis(), fileItem);
        String url = fileItem.getUrl();
        column.setCover_img(url);
        column.setCreateBy(userID);
        System.out.println(url);
        int count = videoService.insertColumn(column);
        return "create-column";
    }

    @PostMapping("/video-form")
    public String video_form(@RequestParam("to_upload_token") String clientToken, Model model, HttpSession session, @UserIDArguments Integer userID, VideoBean video, @RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2) {
        String serverToken = (String) session.getAttribute("to_upload_token");
        if (serverToken == null) { // 重复提交了
            return "video-upload";
        }
        if (!serverToken.equals(clientToken)) { // 重复提交了
            return "video-upload";
        }
        // 提交了就移除session中的token令牌
        session.removeAttribute("to_upload_token");
        FileClient fileClient = new FileClient();
        FileItem fileItem = null;
        FileItem fileItem2 = null;
        try {
            fileItem = new FileItem(file.getOriginalFilename(), file.getInputStream());
            fileItem2 = new FileItem(file2.getOriginalFilename(), file2.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileItem = fileClient.uploadFile(System.currentTimeMillis(), fileItem);
        fileItem2 = fileClient.uploadFile(System.currentTimeMillis(), fileItem2);
        video.setCover_img(fileItem.getUrl());
        video.setVideo_url(fileItem2.getUrl());
        video.setUserID(userID);
        int count = videoService.insertVideo(video);
        return "video-upload";
    }

    // 增加播放次数
    @PostMapping("/playCount")
    public int playCount(Integer videoID) {
        return videoService.addPlayCount(videoID);
    }

    // 到达专栏管理页面
    @RequestMapping("/toColumnManagerList")
    public String toColumnManagerList(Model model, @UserIDArguments Integer userID) {
        List<SpecialColumnBean> columnList = specialColumnService.getListByUserID(userID);
        model.addAttribute("columnList", columnList);
        return "manager-column";
    }

    // 专栏点击跳转到视频播放页面要特殊处理
    @RequestMapping("/columnPlayHandle")
    @ResponseBody
    public List<VideoBean> columnPlayHandle(@RequestParam("columnID") Integer columnID) {
        return videoService.getVideosBySpeID(columnID);
    }

    // 删除专栏
    @DeleteMapping("/deleteColumn")
    @ResponseBody
    public Result deleteColumn(@RequestParam("columnID") Integer columnID) {
        Integer[] ids = new Integer[1];
        ids[0] = columnID;
        return specialColumnService.delete(ids);
    }

    // 到达视频管理页面
    @RequestMapping("/toVideoManagerList")
    public String toVideoManagerList(Model model, @UserIDArguments Integer userID) {
        List<VideoBean> videoList = videoService.getVideoByUserID(userID);
        model.addAttribute("videoList", videoList);
        return "manager-video";
    }

    // 删除视频
    @DeleteMapping("/deleteVideo")
    @ResponseBody
    public Result deleteVideo(@RequestParam("videoID") Integer videoID) {
        Integer[] ids = new Integer[1];
        ids[0] = videoID;
        return videoService.delete(ids);
    }
}
