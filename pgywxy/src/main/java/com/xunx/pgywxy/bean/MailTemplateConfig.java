package com.xunx.pgywxy.bean;

/**
 * Bean类 - 邮件模板配置
 * ============================================================================
 * 版权所有 2008-2010 长沙鼎诚软件有限公司,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得SHOP++商业授权之前,您不能将本软件应用于商业用途,否则SHOP++将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.shopxx.net
 * ----------------------------------------------------------------------------
 * KEY: SHOPXXB7808BFD2543A7A80440B320DC4A3B9B
 * ============================================================================
 */

public class MailTemplateConfig {

	public static final String SMTP_TEST = "smtpTest";// SMTP邮箱配置测试
	public static final String PASSWORD_RECOVER = "passwordRecover";// 密码找回
	public static final String GOODS_NOTIFY = "goodsNotify";// 到货通知

	private String name;// 配置名称
	private String description;// 描述
	private String subject;// 主题
	private String templatePath;// Freemarker模板文件路径

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}