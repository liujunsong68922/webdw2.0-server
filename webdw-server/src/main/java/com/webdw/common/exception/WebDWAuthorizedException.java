package com.webdw.common.exception;

/**
 * WebDWAuthorizedException 代表可捕获的数据窗口授权异常
 * @author Administrator
 *
 */
public class WebDWAuthorizedException extends Exception {
	private String errString ="";

	public String getErrString() {
		return errString;
	}

	public void setErrString(String errString) {
		this.errString = errString;
	}

	
	// 利用_errString变成构造函数
	public WebDWAuthorizedException(String _errString) {
		this.errString = _errString;
	}
}
