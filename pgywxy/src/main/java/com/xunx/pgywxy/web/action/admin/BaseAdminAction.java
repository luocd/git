package com.xunx.pgywxy.web.action.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionSupport;
import com.xunx.pgywxy.bean.Pager;
import com.xunx.pgywxy.bean.Setting;
import com.xunx.pgywxy.entity.account.Admin;
import com.xunx.pgywxy.util.JsonUtil;
import com.xunx.pgywxy.util.SettingUtil;

/**
 * ClassName:BaseAdminAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2012-8-16 下午10:34:14 <br/>
 * 
 * @author JasonZhang
 * @version
 * @since JDK 1.6
 * @see
 */
public class BaseAdminAction extends ActionSupport {

	/**
	 * serialVersionUID.
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 5657331463109974413L;
	private static final String HEADER_ENCODING = "UTF-8";
	private static final boolean HEADER_NO_CACHE = true;
	private static final String HEADER_TEXT_CONTENT_TYPE = "text/plain";
	private static final String HEADER_JSON_CONTENT_TYPE = "text/plain";

	public static final String VIEW = "view";
	public static final String LIST = "list";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String REDIRECT = "redirect";

	protected String id;
	protected String[] ids;
	protected Pager pager = new Pager();
	protected String logInfo;// 日志记录信息
	protected String redirectUrl;// 跳转URL

	public static final String STATUS_PARAMETER_NAME = "status";// 操作状态参数名称
	public static final String MESSAGE_PARAMETER_NAME = "message";// 操作消息参数名称

	// 操作状态（警告、错误、成功）
	public enum Status {
		warn, error, success
	}

	/**
	 * 
	 * getSetting: 获取系统配置信息. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @return
	 * @since JDK 1.6
	 */
	public Setting getSetting() {
		return SettingUtil.getSetting();
	}

	// 判断是否为添加
	public Boolean getIsAddAction() {
		if (id == null) {
			return true;
		}
		else {
			return false;
		}
	}

	// 判断是否为编辑
	public Boolean getIsEditAction() {
		if (id != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	// 获取Parameter
	protected String getParameter(String name) {
		return ServletActionContext.getRequest().getParameter(name);
	}

	/**
	 * 获取ServletContext getServletContext:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author JasonZhang
	 * @return
	 * @since JDK 1.6
	 */
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	// AJAX输出
	protected String ajax(String content, String contentType) {
		try {
			HttpServletResponse response = initResponse(contentType);
			response.getWriter().write(content);
			response.getWriter().flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	// 根据文本内容输出AJAX
	protected String ajax(String text) {
		return ajax(text, HEADER_TEXT_CONTENT_TYPE);
	}

	// 根据操作状态输出AJAX
	protected String ajax(Status status) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
		JsonUtil.toJson(response, jsonMap);
		return NONE;
	}

	// 根据操作状态、消息内容输出AJAX
	protected String ajax(Status status, String message) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
		jsonMap.put(MESSAGE_PARAMETER_NAME, message);
		JsonUtil.toJson(response, jsonMap);
		return NONE;
	}

	// 根据Object输出AJAX
	protected String ajax(Object object) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		JsonUtil.toJson(response, object);
		return NONE;
	}

	// 根据boolean状态输出AJAX
	protected String ajax(boolean booleanStatus) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUS_PARAMETER_NAME, booleanStatus);
		JsonUtil.toJson(response, jsonMap);
		return NONE;
	}

	private HttpServletResponse initResponse(String contentType) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType + ";charset=" + HEADER_ENCODING);
		if (HEADER_NO_CACHE) {
			response.setDateHeader("Expires", 1L);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		}
		return response;
	}

	protected Admin getLoginAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal == null || !(principal instanceof Admin)) {
			return null;
		}
		else {
			return (Admin) principal;
		}
	}

}
