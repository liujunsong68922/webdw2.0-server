package com.webdw.model.dboper;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * 读取数据窗口的配置文件信息，配置信息被放置在t_datawindow这个配置表中
 * 
 * @author Administrator
 *
 */
public class DWConfig {

	// 获取数据库的配置连接对象
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

	// 获取一个字符串的返回值
	private String getSingleStringValue(String sql) throws Exception {
		// 打开数据库连接
		Connection conn = this.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		String sret = "";

		// 获取返回结果
		if (rs.next()) {
			sret = rs.getString(1);
		}

		// 关闭数据库连接
		rs.close();
		stat.close();
		conn.close();
		rs = null;
		stat = null;
		conn = null;
		// return
		return sret;

	}

	/**
	 * 获取数据窗口定义的字符串
	 * 
	 * @param dwname
	 * @return
	 */
	public String getDWSyntaxStringByDWName(String dwname) {
		String strsql = "select dw_define from t_datawindow where dw_name='" + dwname + "'";
		System.out.println(strsql);
		try {
			return this.getSingleStringValue(strsql);
		} catch (Exception e) {
			System.out.println("SQL ERROR:" + e.toString());
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取数据窗口的Select语句定义(带参数)
	 * 
	 * @param dwname
	 * @return
	 */
	public String getDWSelectSQLByDWName(String dwname) {
		String strsql = "select dw_selectsql from t_datawindow where dw_name='" + dwname + "'";
		System.out.println(strsql);
		try {
			return this.getSingleStringValue(strsql);

		} catch (Exception e) {
			System.out.println("SQL ERROR:" + e.toString());
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取数据窗口的参数列表（逗号分隔）
	 * 
	 * @param dwname
	 * @return
	 */
	public String getDWSelectArgsByDWName(String dwname) {
		String strsql = "select dw_selectsql_args from t_datawindow where dw_name='" + dwname + "'";
		System.out.println(strsql);
		try {
			String args = this.getSingleStringValue(strsql);
			if (args == null) {
				args = "";
			}
			return args;
		} catch (Exception e) {
			System.out.println("SQL ERROR:" + e.toString());
			e.printStackTrace();
		}
		return "";
	}

	public void setDWJsonString(String dwname, String jsonstring) {
		String strsql = "Update t_datawindow set dw_define_json=? where dw_name='" + dwname + "'";
		// 打开数据库连接
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(strsql);
			stat.setString(1, jsonstring);
			stat.executeUpdate();
			// 关闭数据库连接
			stat.close();
			conn.close();
			stat = null;
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
