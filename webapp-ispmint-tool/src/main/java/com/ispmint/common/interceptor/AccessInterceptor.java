package com.ispmint.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ispmint.authentication.object.ILoginResponse;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(AccessInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Access..");
		ILoginResponse user = (ILoginResponse) request.getSession().getAttribute("user");
		String requestUrl = request.getRequestURI().substring(request.getContextPath().length());

		if(!requestUrl.contains("login") && !requestUrl.contains("logout") && !requestUrl.contains("site")) {
			if(user != null) {
				return super.preHandle(request, response, handler);
			} else {
				logger.info("Redirect..");
				response.sendRedirect(request.getContextPath()+"/site#/login");
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("Complete..");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Post..");
		super.postHandle(request, response, handler, modelAndView);
	}
}
