package cn.com.carit.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class AttachmentUtil {
	private static final Logger logger = LoggerFactory.getLogger(AttachmentUtil.class);
	private static volatile AttachmentUtil INSTANCE=null;
	private static Properties p = new Properties();
	
	private AttachmentUtil(){}
	
	private AttachmentUtil(String path){
		try {
			logger.info("init AttachmentUtil INSTANCE start...");
			logger.debug(path+"/portal_attachment.properties");
			URL url=new URL(path+"/portal_attachment.properties");
			p.load(url.openStream());
			logger.info("init AttachmentUtil INSTANCE end...");
		} catch (MalformedURLException e) {
			logger.error("read file["+path+"/portal_attachment.properties"+"] error...", e);
		} catch (IOException e) {
			logger.error("init attachment.properties error...", e);
		}
	}
	/**
	 * 初始化
	 * @param path
	 */
	public static void init(String path){
		if (INSTANCE==null) {
			synchronized (AttachmentUtil.class) {
				if (INSTANCE==null) {
					INSTANCE=new AttachmentUtil(path);
				}
			}
		}
	}
	
	public static void mkDir(String path){
		File file=new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static File newFile(String parentPath, String fileName){
		mkDir(parentPath);
		return new File(parentPath+File.separator+fileName);
	}
	
	public static Object getValue(String key) {
		return p.get(key);
	}
	
	public static File getImageFile(String fileName){
		return newFile((String)getValue("attachment.images"), fileName);
	}
	
	public static File getVideoFile(String fileName){
		return newFile((String)getValue("attachment.video"), fileName);
	}
	
	public static File getFlashFile(String fileName){
		return newFile((String)getValue("attachment.flash"), fileName);
	}
	
	public static String getImagePath(String fileName){
		int index=fileName.lastIndexOf(File.separator);
		if (index!=-1) {
			fileName=fileName.substring(index);
		}
		return (String)getValue("attachment.images")+(File.separator+fileName);
	}
	
	public static String getVideoPath(String fileName){
		int index=fileName.lastIndexOf(File.separator);
		if (index!=-1) {
			fileName=fileName.substring(index);
		}
		return (String)getValue("attachment.video")+(File.separator+fileName);
	}
	public static String getFlashPath(String fileName){
		int index=fileName.lastIndexOf(File.separator);
		if (index!=-1) {
			fileName=fileName.substring(index);
		}
		return (String)getValue("attachment.flash")+(File.separator+fileName);
	}
	public static boolean deleteFile(String fileName){
		logger.info("delete file["+fileName+"]...");
		File file = new File(fileName);
		return file.delete();
	}
	
	public static void deleteImage(String fileName){
		if (!StringUtils.hasText(fileName)) {
			logger.warn("empty fileName...");
			return;
		}
		deleteFile(getImagePath(fileName));
	}
	
	public static void deleteVideo(String fileName){
		if (!StringUtils.hasText(fileName)) {
			logger.warn("empty fileName...");
			return;
		}
		deleteFile(getVideoPath(fileName));
	}
	
	public static void deleteFlash(String fileName){
		if (!StringUtils.hasText(fileName)) {
			logger.warn("empty fileName...");
			return;
		}
		deleteFile(getFlashPath(fileName));
	}
	
	public static void deleteImages(String [] nameList){
		if (nameList!=null && nameList.length>0) {
			for (String fileName : nameList) {
				deleteImage(fileName);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init("http://localhost:8080/market");
		System.out.println(getValue("attachment.icons"));
		System.out.println(getImageFile("test"));
		
		System.out.println((int)Math.rint(5.4970));
	}
}
