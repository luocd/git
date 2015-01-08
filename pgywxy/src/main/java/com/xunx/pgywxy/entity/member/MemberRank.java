package com.xunx.pgywxy.entity.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.xunx.pgywxy.entity.BaseEntity;

/**
 * MemberRank.
 */
@Entity
@Table(name = "xunx_member_rank", uniqueConstraints = { @UniqueConstraint(columnNames = "score"),
		@UniqueConstraint(columnNames = "name") })
public class MemberRank extends BaseEntity {

	/**
	* serialVersionUID:TODO(用一句话描述这个变量表示什么).
	* @since JDK 1.6
	*/
	private static final long serialVersionUID = 3695378381362763808L;
	private Boolean isDefault;
	private String name;
	private Double preferentialScale;
	private Integer score;

	@Column(name = "is_default", nullable = false)
	public Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "preferential_scale", nullable = false, precision = 22, scale = 0)
	public Double getPreferentialScale() {
		return this.preferentialScale;
	}

	public void setPreferentialScale(Double preferentialScale) {
		this.preferentialScale = preferentialScale;
	}

	@Column(name = "score", unique = true, nullable = false)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	@Transient
	public void onSave() {

		if (preferentialScale == null || preferentialScale < 0D) {
			preferentialScale = 0D;
		}
		if (score == null || score < 0) {
			score = 0;
		}
		if (isDefault == null) {
			isDefault = false;
		}

	}

	@Override
	@Transient
	public void onUpdate() {

		if (preferentialScale == null || preferentialScale < 0D) {
			preferentialScale = 0D;
		}
		if (score == null || score < 0) {
			score = 0;
		}
		if (isDefault == null) {
			isDefault = false;
		}

	}
}
