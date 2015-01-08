package com.xunx.pgywxy.web.action.admin;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.xunx.pgywxy.service.account.MessageService;

/**
 * ClassName: PageAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2012-8-15 上午12:02:18 <br/>
 * 
 * @version
 * @since JDK 1.6
 */
@ParentPackage("admin")
public class PageAction extends BaseAdminAction {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "messageService")
	private MessageService messageService;

	// 后台主页面
	public String main() {
		return "main";
	}

	// 后台Header
	public String header() {
		return "header";
	}

	// 后台菜单
	public String menu() {
		return "menu";
	}

	// 后台中间(显示/隐藏菜单)
	public String middle() {
		return "middle";
	}

	// 后台首页
	public String index() {
		return "index";
	}

	// 获取未读消息数
	public Long getUnreadMessageCount() {
		return messageService.getUnreadMessageCount(super.getLoginAdmin());
	}

	// 获取JAVA版本
	public String getJavaVersion() {
		return System.getProperty("java.version");
	}

	// 获取系统名称
	public String getOsName() {
		return System.getProperty("os.name");
	}

	// 获取系统构架
	public String getOsArch() {
		return System.getProperty("os.arch");
	}

	// 获取系统版本
	public String getOsVersion() {
		return System.getProperty("os.version");
	}

	// 获取Server信息
	public String getServerInfo() {
		return StringUtils.substring(getServletContext().getServerInfo(), 0, 30);
	}

	// 获取Servlet版本
	public String getServletVersion() {
		return getServletContext().getMajorVersion() + "." + getServletContext().getMinorVersion();
	}
}
