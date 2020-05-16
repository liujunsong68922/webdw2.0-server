package com.webdw.config;

/**
 * MEMO: 这个类是WebDW程序类内部使用的固定常量定义
 * WARNING:修改这个类要非常非常慎重
 * MEMO: 原则上这个类里面定义的常量在代码中应该没有
 * @author liujunsong
 *
 */
public class WebDWConfigVO {
	
	//行数据分割符，应当确保这个字符串不出现在每一行中
	public static final String Webdw_DataFormat_Line_Seperator ="\r\n";
	
	//列数据分割符，应当确保这个字符串不出现在每一列中
	public static final String WebDW_DataFormat_Column_Seperator="\t";
}
