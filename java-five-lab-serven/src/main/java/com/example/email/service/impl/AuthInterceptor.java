package com.example.email.service.impl;

import com.example.email.constant.SystemConstant;
import com.example.email.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserModel userModel = (UserModel) session.getAttribute(SystemConstant.USER_SESSION);
        String uri = request.getRequestURI();
        String error = "";
        if (userModel == null) {
            error = "Please login!";
        }
        else if (!userModel.getAdmin() && uri.startsWith("/admin/")) {
            error = "Access denied!";
        }
        if (error.length() > 0) {
            session.setAttribute(SystemConstant.URI_SESSION, uri);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
