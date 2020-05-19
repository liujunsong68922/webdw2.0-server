package com.webdw.event;

import com.webdw.event.handle.IWebDWEventHandler;
import com.webdw.event.model.WebDWEvent;

/**
 * 这个类是一个事件监听类，负责监听WebDW控件（CWebDWController）产生的事件
 * @author liujunsong
 *
 */
public class WebDWEventManager {
	
	/**
	 * triggerEvent的结果是生成一个数据库操作的SQL命令
	 * @param dwname
	 * @param event
	 * @param row_olddata
	 * @param row_newdata
	 * @return
	 */
	public String triggerEvent(String dwname,String event,String row_olddata,String row_newdata) {
		//step1:判断输入的event是不是合法的event定义
		if(event==null || event.equals("")) {
			System.out.println("Event Argument Is Empty.");
			return "";
		}
		
		//如果输入的事件参数不是Insert,Update,Delete，则报错不处理
		if(!(event.equals(WebDWEvent.InsertEvent) || 
				event.equals(WebDWEvent.UpdateEvent) ||
				event.equals(WebDWEvent.DeleteEvent))){
			System.out.println("Event Argument is invalid:"+event);
			return "";
		}
		
		//Step1: 利用工厂类来获取处理接口
		IWebDWEventHandler eventhandler = WebDWEventHandleFactory.getEventHandler(dwname,event);
		
		//Step1:调用外部的事务处理程序来获取要进行额外处理的sql命令.
		String strsql="";
		if(eventhandler!=null) {
			strsql = eventhandler.getDbSql(dwname,row_olddata,row_newdata);
		}
		
		return strsql;
	}
}
