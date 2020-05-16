package hello.dbinterface.service;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webdw.config.WebDWConfigVO;

import hello.dbinterface.controller.LoginController;


/**
 * 执行数据库上的SELECT命令，按照指定格式返回对应数据 
 * 
 * @author Administrator
 *
 */
@Service
public class DbInterfaceDBSelectOper {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(DbInterfaceDBSelectOper.class);

	private JSONArray getJsonArrayReturnValue(String sql) throws Exception {
		// 打开数据库连�?
		//Connection conn = this.getConnection();
		//Statement stat = conn.createStatement();
		
		SqlRowSet rs =  jdbcTemplate.queryForRowSet(sql);
		String sret = "";

		// 获取返回结果
		SqlRowSetMetaData meta = rs.getMetaData();
		String colname[] = new String[meta.getColumnCount()+1];
		
		String sline = "";
		int col = 0;
		for (col = 1; col <= meta.getColumnCount(); col++) {
			colname[col] = meta.getColumnLabel(col);
		
			if (col < meta.getColumnCount()) {
				sline = sline + meta.getColumnLabel(col) + "\t";
			} else {
				sline = sline + meta.getColumnLabel(col);
			}
		}
		logger.info("sline = " + sline);
		JSONArray retArray = new JSONArray();
		// 获取表头
		sret = sret + sline + WebDWConfigVO.Webdw_DataFormat_Line_Seperator;
		for (; rs.next(); sret = sret + sline + WebDWConfigVO.Webdw_DataFormat_Line_Seperator) {
			JSONObject obj = new JSONObject();
			retArray.add(obj);
			sline = "";
			for (col = 1; col <= meta.getColumnCount(); col++) {
				obj.put(colname[col], rs.getString(col));
				
				if (col < meta.getColumnCount()) {
					sline = sline + rs.getString(col) + "\t";

				} else {
					sline = sline + rs.getString(col);
				}
			}
		}

		return retArray;

	}

	public JSONArray executeSelect(String strsql) throws Exception {
		logger.info(strsql);
		try {
			return this.getJsonArrayReturnValue(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception:{}:{}, Error:{}", e.getClass().getName(), e.getLocalizedMessage(), e.getStackTrace()[0].toString(), e);
			throw new Exception("SQL Error:" + strsql);
		}
	}

}
