package com.xunx.pgywxy.service.account;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunx.pgywxy.bean.Pager;
import com.xunx.pgywxy.dao.account.AdminDao;
import com.xunx.pgywxy.entity.account.Admin;

/**
 * Service实现类 - 管理员
 * ClassName:AdminService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-18 上午11:56:15 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service("adminService")
public class AdminService {

	@Resource(name = "adminDao")
	private AdminDao adminDao;

	@Transactional(readOnly = true)
	public Admin getAdminByUsername(String username) {
		return adminDao.getAdminByUsername(username);
	}

	public Admin getLoginAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal == null || !(principal instanceof Admin)) {
			return null;
		}
		else {
			return (Admin) principal;
		}
	}

	@Transactional(readOnly = true)
	public Admin loadLoginAdmin() {
		Admin admin = getLoginAdmin();
		if (admin == null) {
			return null;
		}
		else {
			return adminDao.get(admin.getId());
		}
	}

	@Transactional(readOnly = true)
	public boolean isExistByUsername(String username) {
		return adminDao.isExistByUsername(username);
	}

	@Transactional
	public String save(Admin admin) {
		return adminDao.saveEntity(admin);
	}

	@Transactional
	public void update(Admin admin) {
		adminDao.updateEntity(admin);
	}

	@Transactional(readOnly = true)
	public Admin load(String id) {
		return adminDao.get(id);
	}

	@Transactional
	public void delete(Admin admin) {
		adminDao.delete(admin);
	}

	@Transactional(readOnly = true)
	public Pager findPager(Pager pager) {
		return adminDao.findPager(pager);
	}

	@Transactional(readOnly = true)
	public Long getTotalCount() {
		return adminDao.getTotalCount();
	}
}
