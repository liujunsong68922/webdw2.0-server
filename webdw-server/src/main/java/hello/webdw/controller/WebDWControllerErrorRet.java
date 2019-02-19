package hello.webdw.controller;

import java.util.ArrayList;

public class WebDWControllerErrorRet extends WebDWControllerRet {
	
	private WebDWControllerErrorRet() {
		// unreachable.
	}
	
	/**
	 * 按照错误返回对象模式构建数据对象
	 * @param status
	 * @param msg
	 */
	public WebDWControllerErrorRet(int status,String msg) {
		super();
		this.status = status;
		this.message = msg;
		this.uuid = "";
		this.uiobjList = new ArrayList();
	}
}
