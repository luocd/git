package com.xunx.pgywxy.bean;

import org.apache.commons.lang.StringUtils;

import com.xunx.pgywxy.util.CommonUtil;

/**
 * ClassName:Setting <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2012-8-16 上午12:15:34 <br/>
 * 
 * @version
 * @since JDK 1.6
 * @see
 */
public class Setting implements java.io.Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -5097797120489882937L;
	private String systemName;// 系统名称
	private String systemVersion;// 系统版本
	private String systemDescription;// 系统描述
	private String contextPath;// 虚拟路径
	private String imageUploadPath;// 图片上传路径
	private String imageBrowsePath;// 图片浏览路径
	private String adminLoginUrl;// 后台登录URL
	private String adminLoginProcessingUrl;// 后台登录处理URL
	private String smtpFromMail;// 发件人邮箱
	private String smtpHost;// SMTP服务器地址
	private Integer smtpPort;// SMTP服务器端口
	private String smtpUsername;// SMTP用户名
	private String smtpPassword;// SMTP密码
	private Boolean isLoginFailureLock; // 是否开启登录失败锁定账号功能
	private Integer loginFailureLockCount;// 同一账号允许连续登录失败的最大次数,超出次数后将锁定其账号
	private Integer loginFailureLockTime;// 账号锁定时间(单位: 分钟,0表示永久锁定)
	private String shopName;// 网店名称
	private String shopUrl;// 网店网址
	private String shopLogoPath;// 网店LOGO路径

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getImageUploadPath() {
		return imageUploadPath;
	}

	public void setImageUploadPath(String imageUploadPath) {
		this.imageUploadPath = imageUploadPath;
	}

	public String getImageBrowsePath() {
		return imageBrowsePath;
	}

	public void setImageBrowsePath(String imageBrowsePath) {
		this.imageBrowsePath = imageBrowsePath;
	}

	public String getAdminLoginUrl() {
		return adminLoginUrl;
	}

	public void setAdminLoginUrl(String adminLoginUrl) {
		this.adminLoginUrl = adminLoginUrl;
	}

	public String getAdminLoginProcessingUrl() {
		return adminLoginProcessingUrl;
	}

	public void setAdminLoginProcessingUrl(String adminLoginProcessingUrl) {
		this.adminLoginProcessingUrl = adminLoginProcessingUrl;
	}

	public String getSmtpFromMail() {
		return smtpFromMail;
	}

	public void setSmtpFromMail(String smtpFromMail) {
		this.smtpFromMail = smtpFromMail;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpUsername() {
		return smtpUsername;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	// 获取图片上传真实路径
	public String getImageUploadRealPath() {
		return CommonUtil.getPreparedStatementPath(imageUploadPath);
	}

	public Boolean getIsLoginFailureLock() {
		return isLoginFailureLock;
	}

	public void setIsLoginFailureLock(Boolean isLoginFailureLock) {
		this.isLoginFailureLock = isLoginFailureLock;
	}

	public Integer getLoginFailureLockCount() {
		return loginFailureLockCount;
	}

	public void setLoginFailureLockCount(Integer loginFailureLockCount) {
		this.loginFailureLockCount = loginFailureLockCount;
	}

	public Integer getLoginFailureLockTime() {
		return loginFailureLockTime;
	}

	public void setLoginFailureLockTime(Integer loginFailureLockTime) {
		this.loginFailureLockTime = loginFailureLockTime;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = StringUtils.removeEnd(shopUrl, "/");
	}

	public String getShopLogoPath() {
		return shopLogoPath;
	}

	public void setShopLogoPath(String shopLogoPath) {
		this.shopLogoPath = shopLogoPath;
	}

}
