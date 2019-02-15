package com.webdw.model.dboper;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.webdw.common.exception.WebDWException;

/**
 * 执行数据库上的SELECT命令，按照指定格式返回对应数据
 * @author Administrator
 *
 */
public class DBSelectOper {

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

	//获取一个字符串的返回值
	private String getStringReturnValue(String sql) throws Exception {
		//打开数据库连接
		Connection conn = this.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		String sret ="";
		
		//获取返回结果
		ResultSetMetaData meta = rs.getMetaData();
		String sline = "";
		int col=0;
		for (col = 1; col <= meta.getColumnCount(); col++)
			if (col < meta.getColumnCount()) {
				sline = sline + meta.getColumnName(col) + "\t";
			}else {
				sline = sline + meta.getColumnName(col);
			}
		
		System.out.println("sline = " + sline);
		//获取表头
		sret = sret + sline + "\r\n";
		for (; rs.next() ; sret = sret + sline + "\r\n") {
			sline = "";
			for (col = 1; col <= meta.getColumnCount(); col++)
				if (col < meta.getColumnCount()) {
					sline = sline + rs.getString(col) + "\t";
				}else {
					sline = sline + rs.getString(col);
				}
		}


		//关闭数据库连接
		rs.close();
		stat.close();
		conn.close();
		rs = null;
		stat = null;
		conn = null;
		//return
		return sret;
		
	}


	
	public String executeSelect(String strsql) throws WebDWException {
		System.out.println(strsql);
		try {
			return this.getStringReturnValue(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebDWException("SQL Error:"+strsql);
		}
	}
		
	
}
