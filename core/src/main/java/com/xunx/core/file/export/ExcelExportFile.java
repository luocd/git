/**
 * @(#)ExcelExportFile.java 2015年1月7日
 * 
 * Copyright 2000-2015 by pgywxy Corporation.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * pgywxy Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with pgywxy.
 * 
 */

package com.xunx.core.file.export;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dony
 * @date 2015年1月7日
 * @version $Revision$
 */
public class ExcelExportFile<T> extends AbstractExportFile<T> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ExcelExportFile(String[] titles, String[] props, OutputStream out, List<T> records) {
		super(titles, props, out, records);
	}

	@Override
	protected void afterGenerate() {
		// do nothing

	}

	@Override
	protected void beforeGenerate() {
		// do nothing

	}

	@Override
	protected void doGenerate() {
		WritableWorkbook workBook = null;
		WritableSheet sheet = null;
		try {
			workBook = Workbook.createWorkbook(super.out);
			// 创建excel工作单元
			sheet = workBook.createSheet("sheet", 0);
		}
		catch (IOException e) {
			logger.error("error with doGenerate", e);
		}
		for (int i = 0; i < records.size(); i++) {
			Object record = records.get(i);

			try {
				for (int j = 0; j < props.length; j++) {
					if (props[j] != "") {
						Object obj = PropertyUtils.getProperty(record, props[j]);
						Label label = new Label(j, i, obj == null ? "" : obj.toString());
						sheet.addCell(label);
					}

				}
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			catch (RowsExceededException e) {
				e.printStackTrace();
			}
			catch (WriteException e) {
				e.printStackTrace();
			}
			finally {
				if (workBook != null) {
					try {
						workBook.write();// 写入文件
						workBook.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (super.out != null) {
					try {
						super.out.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}