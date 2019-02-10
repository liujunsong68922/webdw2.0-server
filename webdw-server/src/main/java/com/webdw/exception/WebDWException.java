package com.webdw.exception;

/**
 * WebDWException 代表可捕获的业务逻辑类异常，推送异常信息给前台（显示或不显示）
 * @author Administrator
 *
 */
public class WebDWException extends Exception {
	private String errString ="";

	public String getErrString() {
		return errString;
	}

	public void setErrString(String errString) {
		this.errString = errString;
	}

	
	// 利用_errString变成构造函数
	public WebDWException(String _errString) {
		this.errString = _errString;
	}
}
