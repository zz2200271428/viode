package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.SpecialCollectBean;
import com.znsd.instapundit.bean.StoreCollectBean;
import com.znsd.instapundit.bean.VideoCollectBean;
import com.znsd.instapundit.service.SpecialCollectService;
import com.znsd.instapundit.service.StoreCollectService;
import com.znsd.instapundit.service.VideoCollectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/collect")
public class CollectController {

    @Reference
    private VideoCollectService videoCollectService;

    @Reference
    private SpecialCollectService specialCollectService;

    @Reference
    private StoreCollectService storeCollectService;


    @RequestMapping("/list")
    public String getCollectPage(Model model){
        Integer id = AppUserController.USER_ID;
        List<VideoCollectBean> video = videoCollectService.ListCollectByUserID(id);
        model.addAttribute("video",video);
        return "my-collect";
    }

    @RequestMapping("/video")
    public String getVideoCollect(Model model){
        Integer id = AppUserController.USER_ID;
        List<VideoCollectBean> video = videoCollectService.ListCollectByUserID(id);
        model.addAttribute("video",video);
        return "my-collect::video_collect";
    }

    @RequestMapping("/special")
    public String getSpecialCollect(Model model){
        Integer id = AppUserController.USER_ID;
        List<SpecialCollectBean> special = specialCollectService.getCollectByUserId(id);
        model.addAttribute("special",special);
        return  "my-collect::special_collect";
    }

    @RequestMapping("/store")
    public String getStoreCollect(Model model){
        Integer id = AppUserController.USER_ID;
        List<StoreCollectBean> store  = storeCollectService.getCollectByUserId(id);
        model.addAttribute("store",store);
        return  "my-collect::store_collect";
    }
}
