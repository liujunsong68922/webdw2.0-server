package hello.webdw.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webdw.controller.DataWindowController;
import com.webdw.model.syntaxmodel.dwsyntax.WebDWSyntax;
import com.webdw.common.MyInt;
import com.webdw.common.exception.WebDWAuthorizedException;
import com.webdw.common.exception.WebDWException;
import com.webdw.controller.CWebDWMemCache;
import com.webdw.view.ui.container.MyJPanel;

import hello.webdw.dbutil.WebDWDBUtil;

/**
 * WebDW Controller
 * 
 * @author Liu JunSong(liujunsong@aliyun.com)
 * @Date:2018/12/8
 * @version:1.0 访问路径/webdw/
 * 
 */

@Controller // This means that this class is a Controller
@RequestMapping(path = "/") // This means URL's start with /demo (after Application path)
public class WebDWController {
	// 是否支持默认用户，如果不支持则会报错
	private boolean CONST_DEFAULT_USER_ALLOW = true;
	// 支持默认用户的情况下，token无效会得到默认用户名，传递给后台
	private String CONST_DEFAULT_USERNAME = "guest";

	/**
	 * test only function,show a string value;
	 * 
	 * @return
	 */
	@GetMapping(path = "/")
	public @ResponseBody String Welcome() {
		// This returns a JSON or XML with the users
		String a = "Welcome to webdw's world!";
		return a;
	}

	@GetMapping(path = "/setdataobject")
	public @ResponseBody WebDWControllerRet SetDataobject(@RequestParam String token, @RequestParam String dwname) {

		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			}else {
				//token 失效，返回错误信息
				WebDWControllerRet ret = new WebDWControllerRet();
				ret.status = 500;
				ret.message = "Token Invalid";
				ret.uuid ="";
				ret.uiobjList = new ArrayList();
				return ret;
			}
		}

		DataWindowController webdwui = new DataWindowController();
		try {
			webdwui.DW_SetDataObjectByName(username,dwname);
		} catch (WebDWAuthorizedException e) {
			WebDWControllerRet ret = new WebDWControllerRet() ;
			ret.status = 500;
			ret.message = e.getErrString();
			ret.uuid ="";
			ret.uiobjList = new ArrayList();
			return ret;
			
		}

		System.out.println(webdwui.errString);

		CWebDWMemCache.saveDataWindowController(webdwui);
		return webdwui.retObject;
	}

	/**
	 * retrieve function,
	 * 
	 * @param dwname
	 * 
	 * @return
	 */
	@GetMapping(path = "/retrieve")
	public @ResponseBody WebDWControllerRet Retrieve(
			@RequestParam String token,
			@RequestParam String dwname, @RequestParam String args)
			{
		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			}else {
				//token 失效，返回错误信息
				WebDWControllerRet ret = new WebDWControllerRet();
				ret.status = 500;
				ret.message = "Token Invalid";
				ret.uuid ="";
				ret.uiobjList = new ArrayList();
				return ret;
			}
		}
		
		DataWindowController webdwui = new DataWindowController();

		//step2. call SetDataObject
		try {
			webdwui.DW_SetDataObjectByName(username,dwname);
		} catch (WebDWAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//token 失效，返回错误信息
			WebDWControllerRet ret = new WebDWControllerRet();
			ret.status = 500;
			ret.message = e.getErrString();
			ret.uuid ="";
			ret.uiobjList = new ArrayList();
			return ret;
		}
		System.out.println(webdwui.errString);

		System.out.println("begin to call retrieve function");
		// set DataBuffer
		try {
			webdwui.DW_Retrieve(username,args);
		} catch (WebDWAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WebDWControllerRet ret = new WebDWControllerRet();
			ret.status = 500;
			ret.message = e.getErrString();
			ret.uuid ="";
			ret.uiobjList = new ArrayList();
			return ret;
		}
		CWebDWMemCache.saveDataWindowController(webdwui);
		return webdwui.retObject;
	}

	/**
	 * insert function,
	 * 
	 * @param dwname
	 * 
	 * @return
	 */
	@GetMapping(path = "/insert")
	public @ResponseBody WebDWControllerRet Insert(
			@RequestParam String token,
			@RequestParam String uuid) {

		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			}else {
				//token 失效，返回错误信息
				WebDWControllerRet ret = new WebDWControllerRet();
				ret.status = 500;
				ret.message = "Token Invalid";
				ret.uuid ="";
				ret.uiobjList = new ArrayList();
				return ret;
			}
		}		
		
		
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		try {
			webdwui.DW_InsertRow(username,0);
		} catch (WebDWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//token 失效，返回错误信息
			WebDWControllerRet ret = new WebDWControllerRet();
			ret.status = 500;
			ret.message = e.getErrString();
			ret.uuid ="";
			ret.uiobjList = new ArrayList();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			WebDWControllerRet ret = new WebDWControllerRet();
			ret.status = 500;
			ret.message = e.getMessage();
			ret.uuid ="";
			ret.uiobjList = new ArrayList();
			return ret;			
		}
		return webdwui.retObject;
	}

	@GetMapping(path = "/delete")
	public @ResponseBody WebDWControllerRet Delete(
			@RequestParam String token,@RequestParam String uuid, @RequestParam int rowid)
			{
		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			}else {
				//token 失效，返回错误信息
				WebDWControllerRet ret = new WebDWControllerRet();
				ret.status = 500;
				ret.message = "Token Invalid";
				ret.uuid ="";
				ret.uiobjList = new ArrayList();
				return ret;
			}
		}		
		
		
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		try {
			webdwui.DW_DeleteRow(username,rowid);
		} catch (WebDWAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//token 失效，返回错误信息
			WebDWControllerRet ret = new WebDWControllerRet();
			ret.status = 500;
			ret.message = e.getErrString();
			ret.uuid ="";
			ret.uiobjList = new ArrayList();
			return ret;
		}

		return webdwui.retObject;
	}

	@GetMapping(path = "/setdata")
	public @ResponseBody WebDWControllerRet SetData(@RequestParam String uuid, @RequestParam int rowid,
			@RequestParam int colid, @RequestParam String data) throws Exception {
		System.out.println("enter SetData rowid:" + rowid);
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		webdwui.DW_SetItem(rowid, colid, data);
		return webdwui.retObject;
	}

	@GetMapping(path = "/update")
	public @ResponseBody WebDWControllerRet Update(
			@RequestParam String token,@RequestParam String uuid) {
		System.out.println("enter update...");

		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			}else {
				//token 失效，返回错误信息
				WebDWControllerRet ret = new WebDWControllerRet();
				ret.status = 500;
				ret.message = "Token Invalid";
				ret.uuid ="";
				ret.uiobjList = new ArrayList();
				return ret;
			}
		}		
		
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		try {
			webdwui.DW_Update(username);
		} catch (WebDWAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//token 失效，返回错误信息
			WebDWControllerRet ret = new WebDWControllerRet();
			ret.status = 500;
			ret.message = e.getErrString();
			ret.uuid ="";
			ret.uiobjList = new ArrayList();
			return ret;
		}
		return webdwui.retObject;
	}
}
