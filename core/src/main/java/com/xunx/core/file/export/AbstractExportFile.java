/**
 * @(#)AbstractExportFile.java 2015年1月7日
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
import java.util.List;

/**
 * @author dony
 * @date 2015年1月7日
 * @version $Revision$
 */
public abstract class AbstractExportFile<T> {

	protected String[] titles;
	protected String[] props;
	protected OutputStream out;
	protected List<T> records;

	public AbstractExportFile(String[] titles, String[] props, OutputStream out, List<T> records) {
		super();
		this.titles = titles;
		this.props = props;
		this.out = out;
		this.records = records;
	}

	public void generateFile() throws IOException {
		beforeGenerate();
		doGenerate();
		afterGenerate();
	}

	protected abstract void beforeGenerate();

	protected abstract void doGenerate();

	protected abstract void afterGenerate();
}
