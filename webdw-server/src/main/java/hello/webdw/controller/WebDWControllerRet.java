package hello.webdw.controller;

import java.util.ArrayList;

/**
 * WebDW2.0ControllerRet object
 * @author Administrator
 *
 */
public class WebDWControllerRet {
	// status code,200 OK,500 ERROR
	public int status = 0;
	
	// message info,to show in frontend.
	public String message="";
	
	//uuid,this is the dwstore uuid,used for cache.
	public String uuid="";
	
	//uiobjList is the ui object model generate by controller.
	public ArrayList uiobjList= new ArrayList();
	
	/**
	 * 设置错误信息
	 * @param msg
	 */
	public void setErrorMsg(int errStatus,String msg) {
		this.status = errStatus;
		this.message = msg;
		this.uuid ="";
		this.uiobjList = new ArrayList();
	}
}
