package com.xunx.pgywxy.service.account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunx.pgywxy.bean.Pager;
import com.xunx.pgywxy.dao.account.RoleDao;
import com.xunx.pgywxy.entity.account.Role;

/**
 * ClassName:RoleService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-19 下午7:19:59 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service("roleService")
public class RoleService {
	@Resource(name = "roleDao")
	private RoleDao roleDao;

	@Transactional(readOnly = true)
	public List<Role> getAllList() {
		return roleDao.getAll();
	}

	@Transactional
	public String save(Role role) {
		return roleDao.saveEntity(role);
	}

	@Transactional
	public void update(Role role) {
		roleDao.updateEntity(role);
	}

	@Transactional(readOnly = true)
	public Role load(String id) {
		return roleDao.get(id);
	}

	@Transactional
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Transactional
	public void delete(String[] ids) {
		roleDao.delete(ids);
	}

	@Transactional(readOnly = true)
	public Pager findPager(Pager pager) {
		return roleDao.findPager(pager);
	}
}
