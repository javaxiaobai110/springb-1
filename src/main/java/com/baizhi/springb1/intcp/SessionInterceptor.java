/*
package com.baizhi.springb1.intcp;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("uri="+request.getRequestURI());
        //登录不做拦截
        if(request.getRequestURI().equals("/admin/login") || request.getRequestURI().equals("/admin/image")) {
            return true;
        }
        //验证session是否存在
        Object obj = request.getSession().getAttribute("admin");
        if(obj == null) {
            System.out.println("heheh"+request.getContextPath());
            response.sendRedirect(request.getContextPath()+"/admin/test");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
*/
