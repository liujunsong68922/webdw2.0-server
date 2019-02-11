package com.webdw.common.util;

/**
 * 字符串替换的功能类
 * 
 * @author Administrator
 *
 */
public class SQLStringReplaceUtil {
	/**
	 * 字符串替换的功能类，用于SQL语句中拼装SQL命令，替换变量使用
	 * 
	 * @param inString, 要替换的输入字符串,标识符@name,@code
	 * @param args，用数组表示的多个待替换参数名,例如["name","code"]
	 * @param argvalue，用统一拼装的字符串表示，例如name=aaa&code=bbb&
	 * @return
	 */
	public String Replace(String inString, String args[], String argvalues) {
		String outString = inString;
		System.out.println(outString);
		for(String arg:args) {
			//从输入中获取参数值
			String value = this.getValue(argvalues, arg);
			String arg1 = "@"+arg;
			outString = outString.replaceAll(arg1, value);
			System.out.println(outString);
		}
		
		return outString;
	}
	
	/**
	 * 从拼装起来的字符串里面，获取到指定的变量的名称
	 * @param argvalues 例如name=aaa&code=bbb
	 * @param arg 例如"name"
	 * @return 返回"aaa",找不到返回""
	 */
	public String getValue(String argvalues,String arg) {
		//利用&符号进行输入字符串切分
		String argval[] = argvalues.split("&");
		
		for(String val:argval) {
			if(val.startsWith(arg+"=")) {
				return val.substring(arg.length()+1);
			}
		}
		//找不到，返回""
		return "";
	}
}
