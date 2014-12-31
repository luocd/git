package cn.com.carit.portal.web.interseptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.carit.common.Constants;
import cn.com.carit.common.utils.AttachmentUtil;
import cn.com.carit.portal.bean.AdminUser;

/**
 * 后台系统拦截器
 * @author <a href="mailto:xiegengcai@gmail.com">Gengcai Xie</a>
 *
 */
public class AdminInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log=LoggerFactory.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		if (log.isDebugEnabled()) {
			log.debug("Request for: "+uri);
		}
		String hostPath="http://"+request.getServerName();
		if (log.isDebugEnabled()) {
			log.debug("hostPath: "+hostPath);
		}
		String contexPath="/caritPortal";
		int port=request.getLocalPort();
		if (uri.indexOf(contexPath)!=-1) { // 开发环境
			uri=uri.replaceFirst(contexPath, "");
			hostPath+=":"+port+contexPath;
		}
		// 初始化附件配置
		AttachmentUtil.init(hostPath);
		if (log.isDebugEnabled()) {
			log.debug("Request for: "+uri);
		}
		if (uri.indexOf("admin")!=-1) { // 管理员相关URL
			AdminUser user=(AdminUser) request.getSession().getAttribute(
					Constants.ADMIN_USER);
			if (user==null) { // 没有登录
				if (log.isDebugEnabled()) {
					log.info("not login...");
				}
				//转到登录页面
				request.getRequestDispatcher("/back/loginForm").forward(request, response);
				return false;
			} else {
				return true;
			}
		}
		return super.preHandle(request, response, handler);
	}

}
