package com.xunx.pgywxy.web.action.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.xunx.pgywxy.entity.account.Admin;
import com.xunx.pgywxy.entity.account.Message;
import com.xunx.pgywxy.entity.account.Message.DeleteStatus;
import com.xunx.pgywxy.service.account.AdminService;
import com.xunx.pgywxy.service.account.MessageService;

/**
 * 后台Action类 - 消息
 * ClassName: MessageAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2012-8-21 上午9:59:36 <br/>
 *
 * @version 
 * @since JDK 1.6
 */
@ParentPackage("admin")
public class MessageAction extends BaseAdminAction {

	private static final long serialVersionUID = -8841506249589763663L;

	private Message message;
	private String toMemberUsername;

	@Resource(name = "messageService")
	private MessageService messageService;
	@Resource(name = "adminService")
	private AdminService adminService;

	// 检查用户名是否存在
	public String checkUsername() {
		String toMemberUsername = getParameter("toMemberUsername");
		if (adminService.isExistByUsername(toMemberUsername)) {
			ajax("true");
		}
		else {
			ajax("false");
		}
		return NONE;
	}

	// 发送消息
	public String send() {
		return "send";
	}

	// 回复
	public String reply() {
		message = messageService.load(id);
		//		if (message.getToMember() != null) {
		//			addActionError("参数错误!");
		//			return ERROR;
		//		}
		return "reply";
	}

	// 收件箱
	public String inbox() {
		pager = messageService.getMemberInboxPager(super.getLoginAdmin(), pager);
		return "inbox";
	}

	// 发件箱
	public String outbox() {
		pager = messageService.getMemberOutboxPager(super.getLoginAdmin(), pager);
		return "outbox";
	}

	// 保存
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "toMemberUsername", message = "收件人不允许为空!"),
			@RequiredStringValidator(fieldName = "message.title", message = "标题不允许为空!"),
			@RequiredStringValidator(fieldName = "message.content", message = "消息内容不允许为空!") }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "message.content", maxLength = "10000", message = "消息内容长度超出限制!") })
	@InputConfig(resultName = "error")
	public String save() {
		Admin toMember = adminService.getAdminByUsername(toMemberUsername);
		if (toMember == null) {
			addActionError("收件人不存在!");
			return ERROR;
		}
		message.setToMember(toMember);
		message.setFromMember(super.getLoginAdmin());
		message.setDeleteStatus(DeleteStatus.nonDelete);
		message.setIsRead(false);
		message.setIsSaveDraftbox(false);
		messageService.save(message);
		redirectUrl = "message!outbox.action";
		return SUCCESS;
	}

	// 删除
	public String delete() {
		for (String id : ids) {
			Message message = messageService.load(id);
			if (!message.getIsSaveDraftbox()) {
				if (message.getToMember() == null) {
					if (message.getDeleteStatus() == DeleteStatus.nonDelete) {
						message.setDeleteStatus(DeleteStatus.toDelete);
						messageService.update(message);
					}
					else if (message.getDeleteStatus() == DeleteStatus.fromDelete) {
						messageService.delete(message);
					}
				}
				else if (message.getFromMember() == null) {
					if (message.getDeleteStatus() == DeleteStatus.nonDelete) {
						message.setDeleteStatus(DeleteStatus.fromDelete);
						messageService.update(message);
					}
					else if (message.getDeleteStatus() == DeleteStatus.toDelete) {
						messageService.delete(message);
					}
				}
			}
		}
		return ajax(Status.success, "删除成功!");
	}

	// AJAX获取消息内容
	@InputConfig(resultName = "ajaxError")
	public String ajaxShowMessage() {
		Message message = messageService.load(id);
		//		if (message.getToMember() != null) {
		//			addActionError("参数错误!");
		//			return ERROR;
		//		}
		if (!message.getIsRead()) {
			message.setIsRead(true);
			messageService.update(message);
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUS_PARAMETER_NAME, Status.success);
		jsonMap.put("content", message.getContent());
		return ajax(jsonMap);
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getToMemberUsername() {
		return toMemberUsername;
	}

	public void setToMemberUsername(String toMemberUsername) {
		this.toMemberUsername = toMemberUsername;
	}

}