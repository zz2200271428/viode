package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AskBean;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bxwapp.config.resolver.UserIDArguments;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.AskService;
import com.znsd.instapundit.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Reference
    private StoreService storeService;

    @Reference
    private AskService askService;

    private final Integer ACTIVE_DEFAULT = 0;

    private static final Map<Integer, String> EMPTY_MSG = new HashMap<Integer, String>();

    static {
        EMPTY_MSG.put(0, "该店铺空空如也！！！");
        EMPTY_MSG.put(1, "还没有您的提问！！！");
    }

    @RequestMapping("/to_askshop")
    public String to_askshop(@RequestParam(value = "active", defaultValue = "0") Integer active, Model model) {
        List<StoreBean> storeList = storeService.getStoreByActive(active);
        model.addAttribute("active", active);
        model.addAttribute("storeList", storeList);
        return "askshop";
    }


    /**
     * 跳转到店铺首页
     */
    @RequestMapping("/to_homepage")
    public String to_homepage(@UserIDArguments Integer userID, @RequestParam("storeID") Integer storeID, Model model) {
        List<AskBean> askList = askService.getAskByStoreID(storeID);
        boolean isCollect = storeService.homepageInit(userID, storeID);
        StoreBean store = storeService.getStoreByID(storeID);
        model.addAttribute("store", store);
        model.addAttribute("isCollect", isCollect);
        model.addAttribute("askList", askList);
        model.addAttribute("active", ACTIVE_DEFAULT);
        model.addAttribute("emptyMsg", EMPTY_MSG.get(ACTIVE_DEFAULT));
        return "store-homepage";
    }

    @RequestMapping("/active")
    public String active(@UserIDArguments Integer userID, @RequestParam("active") Integer active, @RequestParam("storeID") Integer storeID, Model model) {
        List<AskBean> askList = active == 0 ? askService.getAskByStoreID(storeID) : askService.getAskByStoreIDAndUserID(storeID, userID);
        StoreBean store = storeService.getStoreByID(storeID);
        boolean isCollect = storeService.homepageInit(userID, storeID);
        model.addAttribute("isCollect", isCollect);
        model.addAttribute("store", store);
        model.addAttribute("askList", askList);
        model.addAttribute("active", active);
        model.addAttribute("emptyMsg", EMPTY_MSG.get(active));
        return "store-homepage::wrapper_refresh";
    }

    @RequestMapping("/collect")
    @ResponseBody
    public boolean collect(@UserIDArguments Integer userID, @RequestParam("storeID") Integer storeID, @RequestParam("flag") boolean flag) {
        return storeService.collect(userID, storeID, flag);
    }

    @RequestMapping("/issue")
    @ResponseBody
    public Result issue(@UserIDArguments Integer userID, @RequestParam("storeID") Integer storeID, @RequestParam("issue") String issue, @RequestParam("reward") BigDecimal reward) {
        return askService.issue(userID, storeID, issue, reward);
    }

}
