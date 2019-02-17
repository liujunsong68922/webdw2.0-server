package hello.webdw.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import com.webdw.common.exception.WebDWAuthorizedException;
import com.webdw.common.exception.WebDWException;
import com.webdw.model.dboper.CEmpty;

public class WebDWDBUtil {

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
	private String getSingleReturnValue(String sql) throws Exception {
		System.out.println(sql);
		// 打开数据库连接
		Connection conn = this.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		String sret = "";

		// 获取返回结果
		ResultSetMetaData meta = rs.getMetaData();
		if(rs.next()) {
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

	public String executeSelect(String strsql) throws WebDWException {
		System.out.println(strsql);
		try {
			return this.getSingleReturnValue(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebDWException("SQL Error:" + strsql);
		}
	}

	/**
	 * 根据Token获取用户名，如果没有，就返回""
	 * 
	 * @param token
	 * @return
	 */
	public String getUserNameByToken(String token) {
		String strsql = "Select username from t_webdw_token_user where token='" + token + "'";
		try {
			String username = this.getSingleReturnValue(strsql);
			return username;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 检查用户是否具有对于指定数据窗口的操作权限
	 * 
	 * @param username
	 * @param dwname
	 * @param oper
	 * @return
	 * @throws WebDWAuthorizedException 
	 */
	public boolean checkUserDwOper(String username, String dwname, String opername) throws WebDWAuthorizedException {
		String strsql = "";
		String sret="";
		// 1.检查username + * 通配符
		strsql = "Select username from t_webdw_user_dwoper " 
				+ " Where username='" + username + "'" 
				+ " And dwoper='"+ "*'";
		try {
			sret = this.getSingleReturnValue(strsql);
			if(sret.length()>0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebDWAuthorizedException("SQL Error:"+strsql);
		}
		
		//step2:check username + dw_name.* 通配符
		strsql = "Select username from t_webdw_user_dwoper " 
				+ " Where username='" + username + "'" 
				+ " And dwoper='"+ dwname+".*'";
		try {
			sret = this.getSingleReturnValue(strsql);
			if(sret.length()>0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebDWAuthorizedException("SQL Error:"+strsql);
		}
		
		
		//step3:check username + dwname.opername
		strsql = "Select username from t_webdw_user_dwoper " 
				+ " Where username='" + username + "'" 
				+ " And dwoper='"+ dwname+"."+opername+"'";
		try {
			sret = this.getSingleReturnValue(strsql);
			if(sret.length()>0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebDWAuthorizedException("SQL Error:"+strsql);
		}
		
		//step4:not found
		return false;
	}

}
