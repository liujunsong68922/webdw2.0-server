package hello.dbinterface.controller;

import javax.servlet.http.HttpServletResponse;

//import org.json.JSONException;
//import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import hello.json.JSONException;
//import hello.json.JSONObject;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/login") // This means URL's start with /demo (after Application path)
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping(path = "/login1")
	public @ResponseBody String login(@RequestParam String username, @RequestParam String password) {

		return "ok";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public LoginRetObj login2(@RequestBody JSONObject jsonParam) throws JSONException {
		logger.info("enter login....");
		logger.info(jsonParam.toString());
		logger.info(jsonParam.getString("userName"));
		logger.info(jsonParam.getString("password"));
		LoginRetObj ret = new LoginRetObj();
		ret.setToken("token2");
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "/get_info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public GetUserInfoRetObj get_Info(@RequestParam String token) {
		logger.info("enter get_info....token=" + token);
		GetUserInfoRetObj ret = new GetUserInfoRetObj();
		ret.setName("super_admin");
		ret.setAccess("super_admin");
		return ret;
	}
}

class LoginRetObj {

	private String token = "";

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

class GetUserInfoRetObj{
	private String avator="";
	private String name="";
	private String user_id="";
	private String access="";
	public String getAvator() {
		return avator;
	}
	public void setAvator(String avator) {
		this.avator = avator;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	
}
