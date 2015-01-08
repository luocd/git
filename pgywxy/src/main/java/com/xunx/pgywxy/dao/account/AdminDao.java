package com.xunx.pgywxy.dao.account;

import org.springframework.stereotype.Repository;

import com.xunx.pgywxy.dao.BaseDao;
import com.xunx.pgywxy.entity.account.Admin;

/**
 * ClassName:AdminDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2012-8-18 上午11:52:49 <br/>
 * 
 * @version
 * @since JDK 1.6
 * @see
 */
@Repository("adminDao")
public class AdminDao extends BaseDao<Admin, String> {

	public Admin getAdminByUsername(String userName) {
		return super.findUniqueBy("username", userName);
	}

	public boolean isExistByUsername(String username) {
		String hql = "from Admin as admin where lower(admin.username) = lower(?)";
		Admin admin = super.findUnique(hql, username);
		return (admin != null);
	}
}
