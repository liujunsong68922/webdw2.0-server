package hello.dbinterface.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import hello.dbinterface.controller.LoginController;
import hello.dbinterface.entity.CommonQuerySQL;
import hello.dbinterface.entity.QuerySQL;
import hello.dbinterface.entity.QuerySQL1;
import hello.dbinterface.entity.QuerySQL1Repository;
import hello.dbinterface.entity.QuerySQL2;
import hello.dbinterface.entity.QuerySQL2Repository;
import hello.dbinterface.entity.QuerySQLRepository;
import hello.dbinterface.exception.QueryException;

@Service
public class QuerySQLService {
	
	//多SQL配置表支持，分别定义了三个sqlReposity
	
	@Autowired
	private QuerySQLRepository querysqlReposity;
	
	@Autowired
	private QuerySQL1Repository querysql1Reposity;

	@Autowired
	private QuerySQL2Repository querysql2Reposity;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * retrieve by queryid ,and replace all argument by args object
	 * @param queryid
	 * @param args
	 * @return
	 * @throws QueryException
	 */
	public String getSQLString(int qid, JSONObject qargs,String sqlconfig) throws QueryException {
		System.out.println("qid="+qid);
		Long id = (long) qid;
		
		if(sqlconfig==null) {
			sqlconfig="";
		}
		//增加对于多配置表的支持，从args里面获取sqlconfig参数
		//sqlconfig=1  读取common_query_sql1
		//sqlconfig=2  读取common_query_sql2
		//其他情况下，读取common_query_sql
		CommonQuerySQL querysqlobj = null;
		
		if(sqlconfig.equals("1")) {
			//sqlconfig=1的情况
			Optional<QuerySQL1> opt = querysql1Reposity.findById(id);
			QuerySQL1 sql1 = opt.get();
			if(sql1!=null) {
				querysqlobj = sql1.toCommonQuerySQL();
			}
		}else  if(sqlconfig.equals("2")) {
			//sqlconfig=2的情况
			Optional<QuerySQL2> opt = querysql2Reposity.findById(id);
			QuerySQL2 sql2 = opt.get();
			if(sql2!=null) {
				querysqlobj = sql2.toCommonQuerySQL();
			}
		}else {
			//其他情况，默认
			System.out.println("id="+id);
			Optional<QuerySQL> opt = querysqlReposity.findById(id);
			QuerySQL sql = opt.get();
			if(sql!=null) {
				querysqlobj = sql.toCommonQuerySQL();
			}
		}

		//检查获取的QuerySQL对象是否为空
		if (querysqlobj == null) {
			throw new QueryException("Cannot find qid:" + qid);
		}

		String strsql = querysqlobj.getQuerysql();
		if (strsql == null || strsql.length() == 0) {
			throw new QueryException("Invalid SQL. queryid:" + qid);
		}

		String qargnames[] = new String[0];
		if (querysqlobj.getQueryargs() == null) {
			// do nothing
		} else {
			qargnames = querysqlobj.getQueryargs().split(","); // 用逗号分隔
		}

		// 进行参数替换，未输入参数，替换为""
		for (int i = 0; i < qargnames.length; i++) {
			String argname = qargnames[i].trim();
			if (argname != null && argname.length() > 0) {
				String argvalue = qargs.getString(qargnames[i]);
				if(argvalue == null) {
					argvalue = "";
				}
				// replace an argname to argvalue
				// support three type of location define
				// 1.support for @,such as @id
				strsql = strsql.replaceAll("@"+argname, argvalue);
				
				// 2.support for $,such as $id
				strsql = strsql.replaceAll("\\$"+argname, argvalue);
				
				// 3.support for {},suan as {id}
				strsql = strsql.replaceAll("\\{"+argname+"}", argvalue);
				logger.info("strsql:"+strsql);
			}
		}
		return strsql;
	}
}
