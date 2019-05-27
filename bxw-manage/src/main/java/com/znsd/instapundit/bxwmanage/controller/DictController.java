package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.DictBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.DictParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 字典管理控制类
 */
@Controller
@RequestMapping("/dict")
public class DictController {

    @Reference
    private DictService dictService;


    /**
     * 返回查询页面,modo设置了一个类型列表
     * @param model
     * @param session
     * @return 页面视图
     */
    @RequestMapping("/lists")
    public String getListPage(Model model, HttpSession session){
        List<DictBean> type = dictService.getLabel();
        model.addAttribute("type",type);
        return "dict/dict-list";
    }

    /**
     * 增加页面
     * @param model
     * @param session
     * @return 页面视图
     */
    @RequestMapping("/addPage")
    public String getAddPage(Model model, HttpSession session){
        return "dict/dict-add";
    }


    /**
     * 修改页面
     * @param model
     * @param session
     * @param id
     * @return 页面视图
     */
    @GetMapping("/editPage/{id}")
    public String getEditPage(Model model, HttpSession session,@PathVariable("id") Integer id){
        DictBean dictBean = dictService.getDictById(id);
        model.addAttribute("dict",dictBean);
        return "dict/dict-add";
    }


    /**
     * 增加字典功能
     * @param dictBean
     * @return Result处理结果
     */
    @PostMapping("/save")
    @ResponseBody
    public Result addDict(DictBean dictBean,HttpSession session){
        UserBean user = (UserBean) session.getAttribute("userSession");
        dictBean.setCreate_by(user.getNickname());
        int insertCount = dictService.addDict(dictBean);
        if (insertCount>0){
            return Result.success(CodeMsg.ADD_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }


    /**
     * 修改功能
     * @param dictBean
     * @return Result处理结果
     */
    @PutMapping("/save")
    @ResponseBody
    public  Result updateDict(DictBean dictBean,HttpSession session){
        UserBean user = (UserBean) session.getAttribute("userSession");
        dictBean.setUpdate_by(user.getNickname());
        int updateCount = dictService.updateDict(dictBean);
        if (updateCount>0){
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }

    /**
     * 删除功能
     * @param ids
     * @return Result处理结果
     */
    //删除字典
    @DeleteMapping("/delete")
    @ResponseBody
    public Result delUser(Integer []ids){
        int delCount = dictService.delete(ids);
        if (delCount>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
       throw new GlobalException(CodeMsg.DELETE_ERROR);
    }


    /**
     * 查询功能，包括模糊搜索和默认搜索
     * @param dictParam
     * @return PagingResult 结果集
     */
    @RequestMapping("/list")
    @ResponseBody
    public PagingResult Pagelist(DictParam dictParam){
        List<DictBean> list = dictService.listPage(dictParam);
        int count  = dictService.getCount(dictParam);
        return PagingResult.success(count,list);
    }

}
