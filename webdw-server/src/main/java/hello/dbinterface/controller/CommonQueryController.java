package hello.dbinterface.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import hello.dbinterface.service.DbInterfaceDBSelectOper;
import hello.dbinterface.service.QuerySQLService;

@Controller
@RequestMapping(path = "/public")
public class CommonQueryController {

	@Autowired
	private DbInterfaceDBSelectOper dbSelectOper;

	@Autowired
	private QuerySQLService querysqlService;
	private static final Logger logger = LoggerFactory.getLogger(CommonQueryController.class);

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public String test() {
		return "TEST-OK";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public JSONArray getData(@RequestBody JSONObject jsonParam) {
		logger.info("enter getData");
		logger.info(jsonParam.toString());
		logger.info(jsonParam.getString("token"));
		JSONArray ret;
		try {
			//获取Qid，即查询ID编号
			int qid = jsonParam.getIntValue("qid");
			//获取Qargs，以json格式描述查询参数，统一以字符串格式描述
			JSONObject qargs = jsonParam.getJSONObject("qargs");
			//sqlconfig代表要从那张SQL配置表来获取SQL模板
			String sqlconfig = jsonParam.getString("sqlconfig");
			//sqlconfig是指向SQL配置表的，系统支持多张SQL配置表并存
			if(sqlconfig == null) {
				sqlconfig ="";
			}
			//获取要执行的SQL语句
			//调用参数为：Qid,查询参数，查询用数据表
			String strsql = querysqlService.getSQLString(qid, qargs,sqlconfig);
			
			//执行SQL语句，目前仅支持查询语句,返回值为一个JSONArray
			ret = dbSelectOper.executeSelect(strsql);
			return ret;
		} catch (Exception e) {
			//TODO:发生异常时记录异常日志到数据库去备查
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception:{}:{}, Error:{}", e.getClass().getName(), e.getLocalizedMessage(), e.getStackTrace()[0].toString(), e);
			return new JSONArray();
		}
	}
}
