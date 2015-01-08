package com.xunx.pgywxy.service.setting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContext;

import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.context.ServletContextAware;

import com.xunx.pgywxy.bean.MailTemplateConfig;
import com.xunx.pgywxy.bean.Setting;
import com.xunx.pgywxy.util.SettingUtil;
import com.xunx.pgywxy.util.TemplateConfigUtil;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.ResourceBundleModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Service实现类 - 邮件服务 ClassName:MailService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2012-8-19 下午4:34:14 <br/>
 * 
 * @version
 * @since JDK 1.6
 * @see
 */
@Service("mailService")
public class MailService implements ServletContextAware {

	private ServletContext servletContext;
	@Resource(name = "freemarkerManager")
	private FreemarkerManager freemarkerManager;
	@Resource(name = "javaMailSender")
	private JavaMailSender javaMailSender;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 
	 * getCommonData:( 获取公共数据). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String, Object> getCommonData() {
		Map<String, Object> commonData = new HashMap<String, Object>();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n");
		ResourceBundleModel resourceBundleModel = new ResourceBundleModel(resourceBundle,
				new BeansWrapper());
		commonData.put("bundle", resourceBundleModel);
		commonData.put("base", getContextPath());
		commonData.put("setting", SettingUtil.getSetting());
		return commonData;
	}

	/**
	 * 
	 * sendSmtpTestMail:(发送测试邮件). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @param smtpFromMail
	 * @param smtpHost
	 * @param smtpPort
	 * @param smtpUsername
	 * @param smtpPassword
	 * @param toMail
	 * @since JDK 1.6
	 */
	public void sendSmtpTestMail(String smtpFromMail, String smtpHost, Integer smtpPort,
			String smtpUsername, String smtpPassword, String toMail) {
		Setting setting = SettingUtil.getSetting();
		Map<String, Object> data = getCommonData();
		MailTemplateConfig mailTemplateConfig = TemplateConfigUtil
				.getMailTemplateConfig(MailTemplateConfig.SMTP_TEST);
		String subject = mailTemplateConfig.getSubject();
		String templatePath = mailTemplateConfig.getTemplatePath();
		try {
			JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) javaMailSender;
			javaMailSenderImpl.setHost(smtpHost);
			javaMailSenderImpl.setPort(smtpPort);
			javaMailSenderImpl.setUsername(smtpUsername);
			javaMailSenderImpl.setPassword(smtpPassword);
			MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
			Configuration configuration = freemarkerManager.getConfiguration(servletContext);
			Template template = configuration.getTemplate(templatePath);
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessageHelper.setFrom(MimeUtility.encodeWord(setting.getShopName()) + " <"
					+ smtpFromMail + ">");
			mimeMessageHelper.setTo(toMail);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(text, true);
			javaMailSender.send(mimeMessage);
		}
		catch (TemplateException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * getContextPath:(获取虚拟路径). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @return
	 * @since JDK 1.6
	 */
	private String getContextPath() {
		if (servletContext.getMajorVersion() < 2
				|| (servletContext.getMajorVersion() == 2 && servletContext.getMinorVersion() < 5)) {
			return SettingUtil.getSetting().getContextPath();
		}
		else {
			return servletContext.getContextPath();
		}
	}
}
