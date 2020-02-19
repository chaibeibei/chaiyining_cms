package com.chaiyining.cms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.chaiyining.cms.service.UserService;
import com.cyn.common.utils.StringUtil;


public class AuthUserInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userInfo = request.getSession().getAttribute(CmsConstant.UserSessionKey);
		if(userInfo!=null) {
			return true;
		}
		//记住登录
		String username = CookieUtil.getCookieByName(request, "username");
		if(StringUtil.isNotBlank(username)) {
			UserService userService = SpringBeanUtils.getBean(UserService.class);
			userInfo = userService.getByUsername(username);
			request.getSession().setAttribute(CmsConstant.UserSessionKey, userInfo);
			return true;
		}
	    response.sendRedirect("/user/login");
		return  false;
	}

}
