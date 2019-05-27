package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.SpecialCollectBean;
import com.znsd.instapundit.param.CollectParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.SpecialCollectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/collect")
public class SpecialCollectController {

    @Reference
    private SpecialCollectService specialCollectService;


    /**
     *
     * @param model
     * @param session
     *@return 页面视图
     * */
   /*
    @RequestMapping("/addPage")
    public String getAddPage(Model model, HttpSession session){
        return "imageContent/image-add";
    }

    @GetMapping("/editPage/{id}")
    public String getEditPage(Model model, HttpSession session,@PathVariable("id") Integer id){
        ImageBean imageBean = imageService.selectImageById(id);
        model.addAttribute("ima",imageBean);
        return  "imageContent/image-add";
    }*/

    @RequestMapping("/lists")
    public String getPageList(Model model, HttpSession session){
        return "collect/column-list";
    }


    //查询分页数据（模糊搜索，默认）
    @RequestMapping("/list")
    @ResponseBody
    public PagingResult SpecialCollectList(CollectParam collectParam){

        List<SpecialCollectBean> specialCollectBeans=specialCollectService.queryList(collectParam);
        int count = specialCollectService.getCount();
        return PagingResult.success(count,specialCollectBeans);
    }

}
