package com.xunx.pgywxy.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;

import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;

/**
 * 
 * ClassName: ImageUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2012-8-19 下午2:18:39 <br/>
 *
 * @version 
 * @since JDK 1.6
 */
public class ImageUtil {

	private static final String JPEG_FORMAT_NAME = "jpg";// JPEG文件格式名称
	private static final String GIF_FORMAT_NAME = "gif";// GIF文件格式名称
	private static final String BMP_FORMAT_NAME = "bmp";// BMP文件格式名称
	private static final String PNG_FORMAT_NAME = "png";// PNG文件格式名称

	/**
	 * 获取图片文件格式
	 * @param imageFile 图片文件
	 * @return 图片文件格式
	 */
	public static String getFormatName(File imageFile) {
		if (imageFile == null || imageFile.length() == 0) {
			return null;
		}
		try {
			String formatName = null;
			ImageInputStream imageInputStream = ImageIO.createImageInputStream(imageFile);
			Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
			if (!iterator.hasNext()) {
				return null;
			}
			ImageReader imageReader = iterator.next();
			if (imageReader instanceof JPEGImageReader) {
				formatName = JPEG_FORMAT_NAME;
			}
			else if (imageReader instanceof GIFImageReader) {
				formatName = GIF_FORMAT_NAME;
			}
			else if (imageReader instanceof BMPImageReader) {
				formatName = BMP_FORMAT_NAME;
			}
			else if (imageReader instanceof PNGImageReader) {
				formatName = PNG_FORMAT_NAME;
			}
			imageInputStream.close();
			return formatName;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 复制图片文件至图片目录
	 * @param imageFile 图片文件
	 * @return 图片文件路径    
	 */
	public static String copyImageFile(ServletContext servletContext, File imageFile) {
		if (imageFile == null) {
			return null;
		}
		String formatName = getFormatName(imageFile);
		if (formatName == null) {
			throw new IllegalArgumentException("imageFile format error!");
		}
		String destImagePath = SettingUtil.getSetting().getImageUploadRealPath() + "/"
				+ CommonUtil.getUUID() + "." + formatName;
		File destImageFile = new File(servletContext.getRealPath(destImagePath));
		File destImageParentFile = destImageFile.getParentFile();
		if (!destImageParentFile.isDirectory()) {
			destImageParentFile.mkdirs();
		}
		try {
			FileUtils.copyFile(imageFile, destImageFile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return destImagePath;
	}

}