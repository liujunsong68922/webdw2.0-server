package com.webdw.event;

import com.webdw.event.handle.IWebDWEventHandler;

/**
 * 工厂类，返回一个特定的处理接口
 * @author liujunsong
 *
 */
public class WebDWEventHandleFactory {
	public static IWebDWEventHandler getEventHandler(String dwname,String dwevent) {
		
		
		
		String classname="";
		//根据数据表定义来获取特定的事件处理类定义，一个事件只对应一个类
		
		
		
		try {
			Object obj = Class.forName(classname).newInstance();
			if(obj instanceof IWebDWEventHandler) {
				return (IWebDWEventHandler)obj;
			}else {
				System.out.println("Object is not instance of IWebDWEventHandler");
				return null;
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
}
