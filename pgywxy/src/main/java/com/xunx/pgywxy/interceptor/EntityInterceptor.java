package com.xunx.pgywxy.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import com.xunx.pgywxy.entity.BaseEntity;
import com.xunx.pgywxy.util.ReflectionUtil;

/**
 * 拦截器 - 自动填充创建日期、修改日期
 * ClassName: EntityInterceptor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2012-8-18 下午10:21:09 <br/>
 *
 * @version 
 * @since JDK 1.6
 */
@Component("entityInterceptor")
public class EntityInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 7319416231145791577L;

	// 保存回调方法
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames,
			Type[] types) {
		if (entity instanceof BaseEntity) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (BaseEntity.CREATE_DATE_PROPERTY_NAME.equals(propertyNames[i])
						|| BaseEntity.MODIFY_DATE_PROPERTY_NAME.equals(propertyNames[i])) {
					state[i] = new Date();
				}
			}
			ReflectionUtil.invokeSetterMethod(entity, BaseEntity.CREATE_DATE_PROPERTY_NAME,
					new Date());
		}
		return true;
	}

	// 更新回调方法
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
			Object[] previousState, String[] propertyNames, Type[] types) {
		if (entity instanceof BaseEntity) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (BaseEntity.MODIFY_DATE_PROPERTY_NAME.equals(propertyNames[i])) {
					currentState[i] = new Date();
				}
			}
			ReflectionUtil.invokeSetterMethod(entity, BaseEntity.MODIFY_DATE_PROPERTY_NAME,
					new Date());
		}
		return true;
	}

}