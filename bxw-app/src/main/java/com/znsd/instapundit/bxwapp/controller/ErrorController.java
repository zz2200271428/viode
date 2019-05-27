package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.znsd.instapundit.result.CodeMsg;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ErrorController extends AbstractErrorController {

    private static final String ERROR_PATH = "/error";
    private static final int OK = 200;

    public ErrorController(ErrorAttributes errorAttributes) {
        super(new DefaultErrorAttributes());
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(ERROR_PATH)
    public ModelAndView ExceptionHandler(HttpServletRequest request, HttpServletResponse response) {
        // 返回成功状态
        response.setStatus(OK);
        CodeMsg cm = (CodeMsg) request.getAttribute("cm");
        // 判断请求类型
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(header);
        if (isAjax) {
            writeJson(response, cm);
            return null;
        } else {
            // 获取程序错码误状态码
            Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
            if (status.equals(404)) {
                return new ModelAndView("error/4xx");
            } else {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("error/5xx");
                mv.addObject("code", cm.getCode());
                mv.addObject("msg", cm.getMsg());
                return mv;
            }
        }

    }

    protected void writeJson(HttpServletResponse response, CodeMsg cm) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.json(cm));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
