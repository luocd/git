package com.xunx.pgywxy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import net.sf.ehcache.Cache;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;

import com.xunx.pgywxy.bean.Setting;

/**
 * ClassName:SettingUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-16 下午10:08:07 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SettingUtil {

	private static final String CACHE_MANAGER_BEAN_NAME = "settingCache";// cacheManager Bean名称
	private static final String SETTING_XML_FILE_NAME = "setting.xml";// setting.xml配置文件名称
	private static final String SETTING_CACHE_KEY = "XUNX_SETTING";// Setting缓存Key

	/**
	 * 读取系统配置信息
	 * 
	 * @return Setting
	 * 
	 * @throws URISyntaxException 
	 * 
	 * @throws DocumentException 
	 * 
	 * @throws IOException 
	 */
	public static Setting readSetting() throws URISyntaxException, DocumentException, IOException {
		File settingXmlFile = new ClassPathResource(SETTING_XML_FILE_NAME).getFile();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(settingXmlFile);
		Node systemNameNode = document.selectSingleNode("/xunx/setting/systemName");
		Node systemVersionNode = document.selectSingleNode("/xunx/setting/systemVersion");
		Node systemDescriptionNode = document.selectSingleNode("/xunx/setting/systemDescription");
		Node contextPathNode = document.selectSingleNode("/xunx/setting/contextPath");
		Node imageUploadPathNode = document.selectSingleNode("/xunx/setting/imageUploadPath");
		Node imageBrowsePathNode = document.selectSingleNode("/xunx/setting/imageBrowsePath");
		Node adminLoginUrlNode = document.selectSingleNode("/xunx/setting/adminLoginUrl");
		Node adminLoginProcessingUrlNode = document
				.selectSingleNode("/xunx/setting/adminLoginProcessingUrl");
		Node shopNameNode = document.selectSingleNode("/xunx/setting/shopName");
		Node shopUrlNode = document.selectSingleNode("/xunx/setting/shopUrl");
		Node shopLogoPathNode = document.selectSingleNode("/xunx/setting/shopLogoPath");
		Node smtpFromMailNode = document.selectSingleNode("/xunx/setting/smtpFromMail");
		Node smtpHostNode = document.selectSingleNode("/xunx/setting/smtpHost");
		Node smtpPortNode = document.selectSingleNode("/xunx/setting/smtpPort");
		Node smtpUsernameNode = document.selectSingleNode("/xunx/setting/smtpUsername");
		Node smtpPasswordNode = document.selectSingleNode("/xunx/setting/smtpPassword");
		Node isLoginFailureLockNode = document.selectSingleNode("/xunx/setting/isLoginFailureLock");
		Node loginFailureLockCountNode = document
				.selectSingleNode("/xunx/setting/loginFailureLockCount");
		Node loginFailureLockTimeNode = document
				.selectSingleNode("/xunx/setting/loginFailureLockTime");

		Setting setting = new Setting();
		setting.setSystemName(systemNameNode.getText());
		setting.setSystemVersion(systemVersionNode.getText());
		setting.setSystemDescription(systemDescriptionNode.getText());
		setting.setContextPath(contextPathNode.getText());
		setting.setImageUploadPath(imageUploadPathNode.getText());
		setting.setImageBrowsePath(imageBrowsePathNode.getText());
		setting.setAdminLoginUrl(adminLoginUrlNode.getText());
		setting.setAdminLoginProcessingUrl(adminLoginProcessingUrlNode.getText());
		setting.setShopName(shopNameNode.getText());
		setting.setShopUrl(shopUrlNode.getText());
		setting.setShopLogoPath(shopLogoPathNode.getText());
		setting.setSmtpFromMail(smtpFromMailNode.getText());
		setting.setSmtpHost(smtpHostNode.getText());
		setting.setSmtpPort(Integer.valueOf(smtpPortNode.getText()));
		setting.setSmtpUsername(smtpUsernameNode.getText());
		setting.setSmtpPassword(smtpPasswordNode.getText());
		setting.setIsLoginFailureLock(Boolean.valueOf(isLoginFailureLockNode.getText()));
		setting.setLoginFailureLockCount(Integer.valueOf(loginFailureLockCountNode.getText()));
		setting.setLoginFailureLockTime(Integer.valueOf(loginFailureLockTimeNode.getText()));

		return setting;
	}

	/**
	 * 获取系统配置信息
	 * 
	 * @return Setting
	 */
	public static Setting getSetting() {
		Setting setting = null;
		Cache settingCache = (Cache) SpringUtil.getBean(CACHE_MANAGER_BEAN_NAME);
		net.sf.ehcache.Element element = settingCache.get(SETTING_CACHE_KEY);
		if (element != null) {
			setting = (Setting) element.getValue();
		}
		else {
			try {
				setting = readSetting();
			}
			catch (URISyntaxException e) {
				e.printStackTrace();

			}
			catch (DocumentException e) {
				e.printStackTrace();

			}
			catch (IOException e) {
				e.printStackTrace();

			}
			element = new net.sf.ehcache.Element(SETTING_CACHE_KEY, setting);
			settingCache.put(element);
		}
		return setting;
	}

	/**
	 * 写入系统配置信息
	 * 
	 * @return Setting
	 */
	public static void writeSetting(Setting setting) {
		File settingXmlFile = null;
		Document document = null;
		try {
			settingXmlFile = new ClassPathResource(SETTING_XML_FILE_NAME).getFile();
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(settingXmlFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Element rootElement = document.getRootElement();
		Element settingElement = rootElement.element("setting");
		Node shopNameNode = document.selectSingleNode("/xunx/setting/shopName");
		Node shopUrlNode = document.selectSingleNode("/xunx/setting/shopUrl");
		Node shopLogoPathNode = document.selectSingleNode("/xunx/setting/shopLogoPath");
		Node isLoginFailureLockNode = document.selectSingleNode("/xunx/setting/isLoginFailureLock");
		Node loginFailureLockCountNode = document
				.selectSingleNode("/xunx/setting/loginFailureLockCount");
		Node loginFailureLockTimeNode = document
				.selectSingleNode("/xunx/setting/loginFailureLockTime");
		Node smtpFromMailNode = document.selectSingleNode("/xunx/setting/smtpFromMail");
		Node smtpHostNode = document.selectSingleNode("/xunx/setting/smtpHost");
		Node smtpPortNode = document.selectSingleNode("/xunx/setting/smtpPort");
		Node smtpUsernameNode = document.selectSingleNode("/xunx/setting/smtpUsername");
		Node smtpPasswordNode = document.selectSingleNode("/xunx/setting/smtpPassword");

		if (shopNameNode == null) {
			shopNameNode = settingElement.addElement("shopName");
		}
		if (shopUrlNode == null) {
			shopUrlNode = settingElement.addElement("shopUrl");
		}
		if (shopLogoPathNode == null) {
			shopLogoPathNode = settingElement.addElement("shopLogoPath");
		}

		if (isLoginFailureLockNode == null) {
			isLoginFailureLockNode = settingElement.addElement("isLoginFailureLock");
		}
		if (loginFailureLockCountNode == null) {
			loginFailureLockCountNode = settingElement.addElement("loginFailureLockCount");
		}
		if (loginFailureLockTimeNode == null) {
			loginFailureLockTimeNode = settingElement.addElement("loginFailureLockTime");
		}

		if (smtpFromMailNode == null) {
			smtpFromMailNode = settingElement.addElement("smtpFromMail");
		}
		if (smtpHostNode == null) {
			smtpHostNode = settingElement.addElement("smtpHost");
		}
		if (smtpPortNode == null) {
			smtpPortNode = settingElement.addElement("smtpPort");
		}
		if (smtpUsernameNode == null) {
			smtpUsernameNode = settingElement.addElement("smtpUsername");
		}
		if (smtpPasswordNode == null) {
			smtpPasswordNode = settingElement.addElement("smtpPassword");
		}

		shopNameNode.setText(setting.getShopName());
		shopUrlNode.setText(setting.getShopUrl());
		shopLogoPathNode.setText(setting.getShopLogoPath());
		isLoginFailureLockNode.setText(String.valueOf(setting.getIsLoginFailureLock()));
		loginFailureLockCountNode.setText(String.valueOf(setting.getLoginFailureLockCount()));
		loginFailureLockTimeNode.setText(String.valueOf(setting.getLoginFailureLockTime()));
		smtpFromMailNode.setText(setting.getSmtpFromMail());
		smtpHostNode.setText(setting.getSmtpHost());
		smtpPortNode.setText(String.valueOf(setting.getSmtpPort()));
		smtpUsernameNode.setText(setting.getSmtpUsername());
		smtpPasswordNode.setText(setting.getSmtpPassword());

		try {
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();// 设置XML文档输出格式
			outputFormat.setEncoding("UTF-8");// 设置XML文档的编码类型
			outputFormat.setIndent(true);// 设置是否缩进
			outputFormat.setIndent("	");// 以TAB方式实现缩进
			outputFormat.setNewlines(true);// 设置是否换行
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(settingXmlFile), outputFormat);
			xmlWriter.write(document);
			xmlWriter.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新系统配置信息
	 * 
	 * @param setting
	 */
	public static void updateSetting(Setting setting) {
		writeSetting(setting);
		Cache settingCache = (Cache) SpringUtil.getBean(CACHE_MANAGER_BEAN_NAME);
		settingCache.remove(SETTING_CACHE_KEY);
	}

	/**
	 * 刷新系统配置信息
	 * 
	 */
	public void flush() {
		Cache settingCache = (Cache) SpringUtil.getBean(CACHE_MANAGER_BEAN_NAME);
		settingCache.remove(SETTING_CACHE_KEY);
	}
}
