package hello.webdw.controller;

import java.util.ArrayList;

/**
 * WebDW2ControllerRet object
 * @author Administrator
 *
 */
public class WebDWControllerRet {
	//uuid,this is the dwstore uuid,used for cache.
	public String uuid="";
	//uiobjList is the ui object model generate by controller.
	public ArrayList uiobjList= new ArrayList();
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public ArrayList getUiobjList() {
		return uiobjList;
	}
	public void setUiobjList(ArrayList uiobjList) {
		this.uiobjList = uiobjList;
	}
}
