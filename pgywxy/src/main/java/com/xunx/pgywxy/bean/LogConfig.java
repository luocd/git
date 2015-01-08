package com.xunx.pgywxy.bean;

public class LogConfig implements java.io.Serializable {

	/**
	 * serialVersionUID.
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -6754592722143989000L;
	private String operation;// 操作名称
	private String actionClass;// Action类名称
	private String actionMethod;// Action方法名称

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}