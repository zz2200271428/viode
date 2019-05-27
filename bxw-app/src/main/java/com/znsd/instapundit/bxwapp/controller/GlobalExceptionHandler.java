package com.znsd.instapundit.bxwapp.controller;

import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.result.CodeMsg;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler/*定义拦截 异常的范围 此时 是拦截所有异常*/
    public String exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof GlobalException) { // 这是全局通用异常处理
            GlobalException ex = (GlobalException) e;
            request.setAttribute("cm", ex.getCm());
        } else if (e instanceof BindException) { // 这是参数校验异常处理
            request.setAttribute("cm", CodeMsg.PARAM_ERROR);
        }  else if (e instanceof SQLException) { // 这是SQL校验异常处理
            request.setAttribute("cm", CodeMsg.SQL_ERROR);
        } else if (e instanceof SpelEvaluationException) { // 这是页面引擎模板异常处理
            request.setAttribute("cm", CodeMsg.PAGE_ERROR);
        } else if (e instanceof TemplateInputException) { // 这是页面引擎模板异常处理
            request.setAttribute("cm", CodeMsg.PAGE_ERROR);
        }else if (e instanceof NullPointerException) { // 这是服务器空值异常处理
            request.setAttribute("cm", CodeMsg.POINE_ERROR);
        } else { // 这是未知错误
            request.setAttribute("cm", CodeMsg.UNKNOWN_ERROR);
        }
        e.printStackTrace();
        return "forward:/error";
    }

}
