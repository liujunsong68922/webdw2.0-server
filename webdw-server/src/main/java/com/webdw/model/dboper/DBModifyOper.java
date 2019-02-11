package com.webdw.model.dboper;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * 执行数据库上的Modify命令，不返回数据
 * @author Administrator
 *
 */
public class DBModifyOper {

	//获取数据库的配置连接对象
	private Connection getConnection() throws Exception {
		String configfile = "com/liu/database.properties";
		String url = "";
		String driver = "";
		String userid = "";
		String passwd = "";
		ClassLoader loader = (new CEmpty()).getClass().getClassLoader();
		java.io.InputStream stream = loader.getResourceAsStream(configfile);
		Properties prop = new Properties();
		prop.load(stream);
		url = prop.getProperty("url");
		driver = prop.getProperty("driver");
		userid = prop.getProperty("userid");
		passwd = prop.getProperty("passwd");
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, userid, passwd);
		return conn;
	}
	
	/**
	 * 执行INSERT,UPDATE,DELETE等更新数据库的操作。
	 * @param strsql
	 * @return
	 */
	public long executeModify(String strsql) {
		
		try {
			//打开数据库连接
			Connection conn = this.getConnection();
			Statement stat = conn.createStatement();
			//设置连接为自动提交
			conn.setAutoCommit(true);
			long ireturn = stat.executeUpdate(strsql);
			
			System.out.println("execute sql:"+strsql);
			System.out.println(ireturn);
			
			if(stat!=null) {
				stat.close();
				stat = null;
			}
			if(conn!=null) {
				conn.close();
				conn = null;
			}
			return ireturn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
		
	
}
