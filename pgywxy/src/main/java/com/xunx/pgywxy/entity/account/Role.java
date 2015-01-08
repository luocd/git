package com.xunx.pgywxy.entity.account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.ForeignKey;

import com.xunx.pgywxy.entity.BaseEntity;
import com.xunx.pgywxy.util.JsonUtil;

/**
 * 实体类 - 管理角色
 * ClassName:Role <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-18 上午11:37:03 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Entity
public class Role extends BaseEntity {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 2115365344935405222L;

	public static final String ROLE_BASE = "ROLE_BASE";// 基础管理权限

	private String name;// 角色名称
	private Boolean isSystem;// 是否为系统内置角色
	private String description;// 描述
	private String authorityListStore;// 权限集合存储

	private Set<Admin> adminSet = new HashSet<Admin>();// 管理员

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	@Column(length = 3000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(mappedBy = "roleSet", fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_role_admin_set")
	public Set<Admin> getAdminSet() {
		return adminSet;
	}

	public void setAdminSet(Set<Admin> adminSet) {
		this.adminSet = adminSet;
	}

	@Column(length = 3000)
	public String getAuthorityListStore() {
		return authorityListStore;
	}

	public void setAuthorityListStore(String authorityListStore) {
		this.authorityListStore = authorityListStore;
	}

	// 获取权限集合
	@SuppressWarnings("unchecked")
	@Transient
	public List<String> getAuthorityList() {
		if (StringUtils.isEmpty(authorityListStore)) {
			return null;
		}
		return JsonUtil.toObject(authorityListStore, ArrayList.class);
	}

	// 设置权限集合
	@Transient
	public void setAuthorityList(List<String> roleList) {
		if (roleList == null || roleList.size() == 0) {
			authorityListStore = null;
			return;
		}
		authorityListStore = JsonUtil.toJson(roleList);
	}

	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if (isSystem == null) {
			isSystem = false;
		}
	}

	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
		if (isSystem == null) {
			isSystem = false;
		}
	}

}
