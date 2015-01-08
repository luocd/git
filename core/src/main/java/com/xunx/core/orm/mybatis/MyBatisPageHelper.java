/**
 * 
 */
package com.xunx.core.orm.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.ReflectionUtils;

import com.xunx.core.orm.Page;

/**
 * Mybatis分页查询工具类,为分页查询增加传递:
 * startRow,endRow : 用于oracle分页使用,从1开始
 * offset,limit : 用于mysql 分页使用,从0开始
 */
@SuppressWarnings("unchecked")
public class MyBatisPageHelper {

	public static <T> Page<T> selectPage(PageMapper pageMapper, Page<T> page, Object parameter) {
		long totalItems = pageMapper.searchPageCount(parameter);

		if (totalItems > 0) {
			List<T> list = pageMapper.searchPageList(toParameterMap(parameter, page));
			page.setResult(list);
			page.setTotalItems(totalItems);
		}
		return page;
	}

	private static Map toParameterMap(Object parameter, Page p) {
		Map map = toParameterMap(parameter);
		map.put("startRow", p.getStartRow());
		map.put("endRow", p.getEndRow());
		map.put("offset", p.getOffset());
		map.put("limit", p.getPageSize());
		return map;
	}

	private static Map toParameterMap(Object parameter) {
		if (parameter instanceof Map) {
			return (Map) parameter;
		}
		else {
			try {
				return PropertyUtils.describe(parameter);
			}
			catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
				return null;
			}
		}
	}
}
