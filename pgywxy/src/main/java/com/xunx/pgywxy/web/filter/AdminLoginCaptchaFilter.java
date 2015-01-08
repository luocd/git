package com.xunx.pgywxy.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.xunx.pgywxy.util.CaptchaUtil;
import com.xunx.pgywxy.util.SettingUtil;

/**
 * 过滤器 - 后台登录验证码
 * ClassName:AdminLoginCaptchaFilter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-18 下午1:50:32 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component("adminLoginCaptchaFilter")
public class AdminLoginCaptchaFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (CaptchaUtil.validateCaptchaByRequest(request)) {
			filterChain.doFilter(request, response);
		}
		else {
			String adminLoginUrl = SettingUtil.getSetting().getAdminLoginUrl();
			response.sendRedirect(request.getContextPath() + adminLoginUrl + "?error=captcha");
		}
	}

	public void destroy() {
	}

}
