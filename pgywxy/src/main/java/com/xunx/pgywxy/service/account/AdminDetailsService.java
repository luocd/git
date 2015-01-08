package com.xunx.pgywxy.service.account;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunx.pgywxy.bean.Setting;
import com.xunx.pgywxy.dao.account.AdminDao;
import com.xunx.pgywxy.entity.account.Admin;
import com.xunx.pgywxy.entity.account.Role;
import com.xunx.pgywxy.util.SettingUtil;

/**
 * ClassName:AdminDetailsService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-18 下午12:00:19 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service("adminDetailsService")
public class AdminDetailsService implements UserDetailsService {

	@Resource(name = "adminDao")
	private AdminDao adminDao;

	@Transactional
	public Admin loadUserByUsername(String username) throws UsernameNotFoundException,
			DataAccessException {
		Admin admin = adminDao.getAdminByUsername(username);
		if (admin == null) {
			throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
		}

		// 解除管理员账户锁定
		Setting setting = SettingUtil.getSetting();
		if (admin.getIsAccountLocked() == true) {
			if (setting.getIsLoginFailureLock() == true) {
				int loginFailureLockTime = setting.getLoginFailureLockTime();
				if (loginFailureLockTime != 0) {
					Date lockedDate = admin.getLockedDate();
					Date nonLockedTime = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
					Date now = new Date();
					if (now.after(nonLockedTime)) {
						admin.setLoginFailureCount(0);
						admin.setIsAccountLocked(false);
						admin.setLockedDate(null);
						adminDao.save(admin);
					}
				}
			}
			else {
				admin.setLoginFailureCount(0);
				admin.setIsAccountLocked(false);
				admin.setLockedDate(null);
				adminDao.save(admin);
			}
		}
		admin.setAuthorities(getGrantedAuthorities(admin));
		return admin;
	}

	private Set<GrantedAuthority> getGrantedAuthorities(Admin admin) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (Role role : admin.getRoleSet()) {
			for (String authority : role.getAuthorityList()) {
				grantedAuthorities.add(new GrantedAuthorityImpl(authority));
			}
		}
		return grantedAuthorities;
	}

}
