package com.webdw.event.handle;

/**
 * WebDW自定义的事件处理器
 * @author liujunsong
 *
 */
public interface IWebDWEventHandler {
	/**
	 * 根据rowdata来获取一个特定的附加SQL语句，用来构建统一的事务处理逻辑
	 * 一般用来更新另一张对应的数据表
	 * @param rowdata，这个rowdata是一个JSON格式的字符串
	 * @return
	 */
	public String getDbSql(String dwname,String row_olddata,String row_newdata);
}
