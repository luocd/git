/**
 * 
 */
package com.xunx.core.orm.mybatis;

import java.util.List;
import java.util.Map;

/**
 * 用于分页的接口
 * @param <T>
 */
@SuppressWarnings("unchecked")
public interface PageMapper<T> {

	List<T> searchPageList(Map parameter);

	long searchPageCount(Object parameter);
}
