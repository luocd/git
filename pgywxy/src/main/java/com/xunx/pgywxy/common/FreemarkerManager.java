package com.xunx.pgywxy.common;

import javax.servlet.ServletContext;

import com.xunx.pgywxy.directive.CheckboxDirective;
import com.xunx.pgywxy.directive.PaginationDirective;
import com.xunx.pgywxy.directive.SubstringMethod;
import com.xunx.pgywxy.util.SpringUtil;

import freemarker.template.TemplateException;

/**
 * ClassName:FreemarkerManager <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-19 下午1:11:28 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FreemarkerManager extends org.apache.struts2.views.freemarker.FreemarkerManager {

	public synchronized freemarker.template.Configuration getConfiguration(
			ServletContext servletContext) throws TemplateException {
		freemarker.template.Configuration config = (freemarker.template.Configuration) servletContext
				.getAttribute(CONFIG_SERVLET_CONTEXT_KEY);
		if (config == null) {
			config = createConfiguration(servletContext);
			// config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

			SubstringMethod substringMethod = (SubstringMethod) SpringUtil
					.getBean("substringMethod");
			CheckboxDirective checkboxDirective = (CheckboxDirective) SpringUtil
					.getBean("checkboxDirective");
			PaginationDirective paginationDirective = (PaginationDirective) SpringUtil
					.getBean("paginationDirective");

			config.setSharedVariable(SubstringMethod.TAG_NAME, substringMethod);
			config.setSharedVariable(CheckboxDirective.TAG_NAME, checkboxDirective);
			config.setSharedVariable(PaginationDirective.TAG_NAME, paginationDirective);

			servletContext.setAttribute(CONFIG_SERVLET_CONTEXT_KEY, config);
		}
		config.setWhitespaceStripping(true);
		return config;
	}
}
