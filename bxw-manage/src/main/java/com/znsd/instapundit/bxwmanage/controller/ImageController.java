package com.znsd.instapundit.bxwmanage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.*;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.DictParam;
import com.znsd.instapundit.param.ImageParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Reference
    private ImageService imageService;

    /**
     *
     * @param model
     *@param session
     *@return 页面视图
     * */
    @RequestMapping("/lists")
    public String getPageList(Model model, HttpSession session){
        return "imageContent/image-list";
    }

    @RequestMapping("/addPage")
    public String getAddPage(Model model, HttpSession session){
        return "imageContent/image-add";
    }

    @GetMapping("/editPage/{id}")
    public String getEditPage(Model model, HttpSession session,@PathVariable("id") Integer id){
        ImageBean imageBean = imageService.selectImageById(id);
        model.addAttribute("ima",imageBean);
        return  "imageContent/image-add";
    }


    /*
    *    //  查询所有的图文列表
    * */
   /* @GetMapping("/imagelist")
    @ResponseBody
    public PagingResult queryAll(PageBean pageBean){
        Map<String,Object> map = new HashMap<String, Object>();
        List<ImageBean> list = imageService.queryListImage(pageBean);
        int count = imageService.getCount();
        return PagingResult.success(count,list);

    }*/
    /*
    *   增加功能
    *
    * */
    @PostMapping("/addimage")
    @ResponseBody
    public Result addImageContent(ImageBean image,HttpSession session){
        UserBean user = (UserBean) session.getAttribute("userSession");
        image.setCreate_by(user.getNickname());
        Map<String,Object> result = new HashMap<String, Object>();
        int delCount=imageService.addImage(image);
        if (delCount>0){
            return Result.success(CodeMsg.ADD_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }
    /*
    *   修改
    * */
    @PutMapping("/addimage")
    @ResponseBody
    public Result updateImageContent(ImageBean image,HttpSession session){
        UserBean user = (UserBean) session.getAttribute("userSession");
        image.setUpdate_by(user.getNickname());
        Map<String,Object> result = new HashMap<String, Object>();
        int delCount=imageService.saveImage(image);
        if (delCount>0){
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }



    /*
     *   修改状态
     *
     * */
    @PutMapping("/update")
    @ResponseBody
    public Result updateStatus(ImageBean imageBean,HttpSession session) {
        // this.redisServer.deleteCacheWithPattern(CacheTactics.VIDEO_PREFIX);
        // return this.videoService.updateStatus(video);
        UserBean user = (UserBean) session.getAttribute("userSession");
        imageBean.setUpdate_by(user.getNickname());
        int delCount= imageService.updateStatus(imageBean);
        if (delCount>0){
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.UPDATE_ERROR);
    }


    //删除字典
    @DeleteMapping("/delete")
    @ResponseBody
    public Result delImage(Integer []ids){
        Map<String,Object> result = new HashMap<String, Object>();
        int delCount = imageService.deleteImage(ids);
        if (delCount>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }




    //查询分页数据（模糊搜索，默认）
    @RequestMapping("/imagelist")
    @ResponseBody
    public PagingResult PageLikelist(ImageParam imageParam){
        List<ImageBean> list = imageService.queryLikeImage(imageParam);
        int count = imageService.getCount();
        return PagingResult.success(count,list);
    }




}
