package com.xunx.pgywxy.web.action.admin;

import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * ClassName:MenuAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-15 下午11:50:18 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@ParentPackage("admin")
public class MenuAction extends BaseAdminAction {
	private static final long serialVersionUID = 6465259795235263493L;

	// 商店配置
	public String setting() {
		return "setting";
	}

	// 会员
	public String member() {
		return "member";
	}

	// 商品
	public String goods() {
		return "goods";
	}

	// 页面管理
	public String content() {
		return "content";
	}

	// 订单管理
	public String order() {
		return "order";
	}

	// 管理员
	public String admin() {
		return "admin";
	}

	// 主体
	public String main() {
		return "main";
	}

	// 头部
	public String header() {
		return "header";
	}

	public String home() {
		return "home";
	}

	public String product() {
		return "product";
	}
}
