package com.znsd.instapundit.bxwapp.config.resolver;

import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.result.CodeMsg;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserIDArgumentsResolver implements HandlerMethodArgumentResolver {
    /**
     * 解析器是否支持当前参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserIDArguments.class);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if (methodParameter.getParameterType() == Integer.class) {
            HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
            AppUserBean user = (AppUserBean) request.getSession().getAttribute("user");
            if (user == null) {
                throw new GlobalException(CodeMsg.QX_ERROR);
            }
            return user.getId();
        }
        return null;
    }
}
