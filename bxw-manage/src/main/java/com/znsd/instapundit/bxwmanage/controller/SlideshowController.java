package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.SlideshowBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.ImageParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.SlideshowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/slideshow")
public class SlideshowController {


    @Reference
    private SlideshowService slideshowService;

    @RequestMapping("/lists")
    public String getPageList(Model model, HttpSession session){
        return "slideshow/slideshow-list";
    }

    @RequestMapping("/addPage")
    public String getAddPage(Model model, HttpSession session){
        return "slideshow/slideshow-add";
    }

    @GetMapping("/editPage/{id}")
    public String getEditPage(Model model, HttpSession session,@PathVariable("id") Integer id){
        SlideshowBean slideshowBean= slideshowService.selectSlideshowById(id);
        model.addAttribute("sds",slideshowBean);
        return  "slideshow/slideshow-add";
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
    @PostMapping("/addSlideshow")
    @ResponseBody
    public Result addImageContent(SlideshowBean slideshowBean,HttpSession session){
        UserBean user = (UserBean) session.getAttribute("userSession");
        slideshowBean.setCreate_by(user.getNickname());
        Map<String,Object> result = new HashMap<String, Object>();
        int delCount=slideshowService.addSlideshow(slideshowBean);
        if (delCount>0){
            return Result.success(CodeMsg.ADD_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }
    /*
     *   修改
     * */
    @PutMapping("/addSlideshow")
    @ResponseBody
    public Result updateImageContent(SlideshowBean slideshowBean,HttpSession session){
        UserBean user = (UserBean) session.getAttribute("userSession");
        slideshowBean.setUpdate_by(user.getNickname());
        Map<String,Object> result = new HashMap<String, Object>();
        int delCount=slideshowService.saveSlideshow(slideshowBean);
        if (delCount>0){
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.UNKNOWN_ERROR);
    }

    //删除字典
    @DeleteMapping("/delete")
    @ResponseBody
    public Result delImage(Integer []ids,SlideshowBean slideshowBean,HttpSession session){
        UserBean user = (UserBean) session.getAttribute("userSession");
        slideshowBean.setUpdate_by(user.getNickname());
        Map<String,Object> result = new HashMap<String, Object>();
        int delCount = slideshowService.deleteSlideshow(ids);
        if (delCount>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }


    /*
     *   修改状态
     *
     * */
    @PutMapping("/update")
    @ResponseBody
    public Result updateStatus(SlideshowBean slideshowBean,HttpSession session) {
        // this.redisServer.deleteCacheWithPattern(CacheTactics.VIDEO_PREFIX);
        // return this.videoService.updateStatus(video);
        UserBean user = (UserBean) session.getAttribute("userSession");
        slideshowBean.setUpdate_by(user.getNickname());
        int delCount= slideshowService.updateStatus(slideshowBean);
        if (delCount>0){
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.UPDATE_ERROR);
    }




    //查询分页数据（模糊搜索，默认）
    @RequestMapping("/slideshowlist")
    @ResponseBody
    public PagingResult PageLikelist(ImageParam slideshowBean){
        List<SlideshowBean> list = slideshowService.queryLikeSlideshow(slideshowBean);
        int count = slideshowService.getCount();
        return PagingResult.success(count,list);
    }




}
