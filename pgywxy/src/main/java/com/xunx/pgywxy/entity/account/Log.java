package com.xunx.pgywxy.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.xunx.pgywxy.entity.BaseEntity;

/**
 * 实体类 - 日志
 * ClassName: Log <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2012-8-21 上午10:32:05 <br/>
 *
 * @version 
 * @since JDK 1.6
 */
@Entity
public class Log extends BaseEntity {

	private static final long serialVersionUID = -4494144902110236826L;

	private String operation;// 操作名称
	private String operator;// 操作员
	private String actionClass;// Action名称
	private String actionMethod;// Action方法名称
	private String ip;// IP
	private String info;// 日志信息

	@Column(nullable = false, updatable = false)
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(nullable = false, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(nullable = false, updatable = false)
	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	@Column(nullable = false, updatable = false)
	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	@Column(nullable = false, updatable = false)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(length = 3000, updatable = false)
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if (operator == null) {
			operator = "未知";
		}
	}

	@Override
	@Transient
	public void onUpdate() {

		// TODO Auto-generated method stub

	}

}