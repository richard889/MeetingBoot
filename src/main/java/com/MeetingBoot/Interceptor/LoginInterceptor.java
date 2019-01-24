package com.MeetingBoot.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, 
							 Object handler) throws Exception {
		
        if (request.getSession().getAttribute("LoginUser") == null) {
            response.sendRedirect("/Login");
            return false;
        }
        return true;
	}
}
