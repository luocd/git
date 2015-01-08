package com.xunx.pgywxy.web.action.admin;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.xunx.pgywxy.bean.Setting;
import com.xunx.pgywxy.service.setting.MailService;
import com.xunx.pgywxy.util.ImageUtil;
import com.xunx.pgywxy.util.SettingUtil;

/**
 * ClassName:SettingAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2012-8-19 上午11:04:13 <br/>
 * 
 * @version
 * @since JDK 1.6
 * @see
 */
@ParentPackage("admin")
public class SettingAction extends BaseAdminAction {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 5245212981076331361L;
	private Setting setting;
	private String smtpToMail;
	private File shopLogo;

	@Resource(name = "mailService")
	private MailService mailService;

	// 发送SMTP测试邮件
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "setting.smtpFromMail", message = "发件人邮箱不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpHost", message = "SMTP服务器地址不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpUsername", message = "SMTP用户名不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpPassword", message = "SMTP密码不允许为空!"),
			@RequiredStringValidator(fieldName = "smtpToMail", message = "收件人邮箱不允许为空!") }, requiredFields = { @RequiredFieldValidator(fieldName = "setting.smtpPort", message = "SMTP服务器端口不允许为空!") }, intRangeFields = { @IntRangeFieldValidator(fieldName = "setting.smtpPort", min = "0", message = "SMTP端口必须为零正整数!") }, emails = {
			@EmailValidator(fieldName = "setting.smtpFromMail", message = "发件人邮箱格式错误!"),
			@EmailValidator(fieldName = "smtpToMail", message = "收件人邮箱格式错误!") })
	@InputConfig(resultName = "ajaxError")
	public String ajaxSendSmtpTest() {
		try {
			mailService.sendSmtpTestMail(setting.getSmtpFromMail(), setting.getSmtpHost(),
					setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(),
					smtpToMail);
		}
		catch (MailAuthenticationException e) {
			return ajax(Status.error, "权限验证失败,请检查SMTP用户名、密码!");
		}
		catch (MailSendException e) {
			return ajax(Status.error, "邮件发送失败,请检查发件人邮箱、SMTP服务器地址、端口!");
		}
		catch (Exception e) {
			return ajax(Status.error, "邮件发送失败!");
		}
		return ajax(Status.success, "测试邮件发送成功,请查收邮件!");
	}

	/**
	 * 
	 * edit:(进入编辑界面前载入原来的属性). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author JasonZhang
	 * @return
	 * @since JDK 1.6
	 */
	public String edit() {
		setting = SettingUtil.getSetting();
		return INPUT;
	}

	// 更新
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "setting.shopName", message = "网店名称不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.shopUrl", message = "网店网址不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpFromMail", message = "发件人邮箱不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpHost", message = "SMTP服务器地址不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpUsername", message = "SMTP用户名不允许为空!") }, requiredFields = {
			@RequiredFieldValidator(fieldName = "setting.isLoginFailureLock", message = "是否开启自动锁定账号功能不允许为空!"),
			@RequiredFieldValidator(fieldName = "setting.loginFailureLockCount", message = "连续登录失败最大次数不允许为空!"),
			@RequiredFieldValidator(fieldName = "setting.loginFailureLockTime", message = "自动解锁时间不允许为空!"),
			@RequiredFieldValidator(fieldName = "setting.smtpPort", message = "SMTP服务器端口不允许为空!") }, intRangeFields = {
			@IntRangeFieldValidator(fieldName = "setting.loginFailureLockCount", min = "1", message = "连续登录失败最大次数必须为正整数!"),
			@IntRangeFieldValidator(fieldName = "setting.loginFailureLockTime", min = "0", message = "自动解锁时间必须为零或正整数!"),
			@IntRangeFieldValidator(fieldName = "setting.smtpPort", min = "0", message = "SMTP端口必须为零或正整数!") }, emails = { @EmailValidator(fieldName = "setting.smtpFromMail", message = "发件人邮箱格式错误!") })
	@InputConfig(resultName = "error")
	public String update() throws Exception {

		Setting persistent = SettingUtil.getSetting();

		if (StringUtils.isNotEmpty(setting.getSmtpPassword())) {
			persistent.setSmtpPassword(setting.getSmtpPassword());
		}
		if (!setting.getIsLoginFailureLock()) {
			persistent.setLoginFailureLockCount(3);
			persistent.setLoginFailureLockTime(10);
		}
		if (shopLogo != null) {
			String shopLogoPath = ImageUtil.copyImageFile(getServletContext(), shopLogo);
			persistent.setShopLogoPath(shopLogoPath);
		}

		BeanUtils.copyProperties(setting, persistent, new String[] { "systemName", "systemVersion",
				"systemDescription", "contextPath", "imageUploadPath", "imageBrowsePath",
				"adminLoginUrl", "adminLoginProcessingUrl", "shopLogoPath" });
		SettingUtil.updateSetting(persistent);

		redirectUrl = "setting!edit.action";
		return SUCCESS;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public String getSmtpToMail() {
		return smtpToMail;
	}

	public void setSmtpToMail(String smtpToMail) {
		this.smtpToMail = smtpToMail;
	}

	public File getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(File shopLogo) {
		this.shopLogo = shopLogo;
	}

}
