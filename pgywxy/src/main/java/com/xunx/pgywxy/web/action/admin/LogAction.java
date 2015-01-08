package com.xunx.pgywxy.web.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.xunx.pgywxy.entity.account.Log;
import com.xunx.pgywxy.service.account.LogService;

/**
 *  后台Action类 - 日志
 * ClassName: LogAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2012-8-21 上午11:42:07 <br/>
 *
 * @version 
 * @since JDK 1.6
 */
@ParentPackage("admin")
public class LogAction extends BaseAdminAction {

	private static final long serialVersionUID = 8784555891643520648L;

	private Log log;

	@Resource(name = "logService")
	private LogService logService;

	// 删除
	public String delete() {
		logService.delete(ids);
		return ajax(Status.success, "删除成功!");
	}

	// 列表
	public String list() {
		pager = logService.findPager(pager);
		return LIST;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

}