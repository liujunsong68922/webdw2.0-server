package com.webdw.event.model;

public class WebDWEvent {
	/**
	 * 插入新数据的事件
	 */
	public static final String InsertEvent="InsertEvent";
	/**
	 * 更新数据的事件
	 */
	public static final String UpdateEvent="UpdateEvent";
	/**
	 * 删除数据的事件
	 */
	public static final String DeleteEvent="DeleteEvent";
	
	/**
	 * 数据窗口名称
	 */
	private String dwname="";
	/**
	 * WebDW事件类型
	 */
	private String eventType="";
	/**
	 * WebDW事件参数1:oldvalue,用json格式封装
	 */
	private String oldvaluejson="";
	
	/**
	 * WebDW事件参数2：newvalue,用json格式封装
	 */
	private String newvaluejson="";
	
	
	public String getDwname() {
		return dwname;
	}


	public void setDwname(String dwname) {
		this.dwname = dwname;
	}


	public String getEventType() {
		return eventType;
	}


	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	public String getOldvaluejson() {
		return oldvaluejson;
	}


	public void setOldvaluejson(String oldvaluejson) {
		this.oldvaluejson = oldvaluejson;
	}


	public String getNewvaluejson() {
		return newvaluejson;
	}


	public void setNewvaluejson(String newvaluejson) {
		this.newvaluejson = newvaluejson;
	}


	public WebDWEvent(String _dwname,String _eventtype,String _oldvalue,String _newvalue) {
		this.dwname = _dwname;
		this.eventType = _eventtype;
		this.oldvaluejson = _oldvalue;
		this.newvaluejson = _newvalue;
	}
}
