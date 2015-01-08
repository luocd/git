/**
 * 
 */
package com.xunx.pgywxy.common;

import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;

import com.xunx.core.orm.hibernate.IgnorePrefixReverseEngineeringStrategy;

/**
 *
 */
public class HbmReverseEngineeringStrategy extends IgnorePrefixReverseEngineeringStrategy {

	public HbmReverseEngineeringStrategy(ReverseEngineeringStrategy delegate) {
		super(delegate);
	}

	/* (non-Javadoc)
	 * @see com.xunx.core.orm.hibernate.IgnorePrefixReverseEngineeringStrategy#getPrefixLength()
	 */
	@Override
	protected int getPrefixLength() {
		//表名以asf_为前缀，默认类名前缀为Acct
		return 5;
	}

}
