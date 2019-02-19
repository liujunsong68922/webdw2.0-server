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
 * @author Liu JunSong
 * @email liujunsong@aliyun.com
 * @Date:2018/12/8
 * @version:1.0 访问路径/webdw/
 * 
 */

@Controller // This means that this class is a Controller
@RequestMapping(path = "/") // 
public class WebDWController {
	// 是否支持默认用户，如果不支持则会报错
	private boolean CONST_DEFAULT_USER_ALLOW = true;
	// 支持默认用户的情况下，token无效会得到默认用户名，传递给后台
	private String CONST_DEFAULT_USERNAME = "guest";

	/**
	 * 测试页面，显示欢迎字符串
	 * 
	 * @return
	 */
	@GetMapping(path = "/")
	public @ResponseBody String index() {
		String a = "Welcome to webdw's world!";
		return a;
	}

	/**
	 * 设置数据窗口对象，控件初始化
	 * 
	 * @param token
	 * @param dwname
	 * @return
	 */
	@GetMapping(path = "/setdataobject")
	public @ResponseBody WebDWControllerRet dw_f1_SetDataObject(@RequestParam String token,
			@RequestParam String dwname) {
		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);
		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			} else {
				return new WebDWControllerErrorRet(500, "Token 无效");
			}
		}

		DataWindowController webdwui = new DataWindowController();
		try {
			webdwui.DW_SetDataObjectByName(username, dwname);
		} catch (WebDWAuthorizedException e) {
			WebDWControllerErrorRet ret = new WebDWControllerErrorRet(500, e.getErrString());
			return ret;
		}

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
	public @ResponseBody WebDWControllerRet dw_f2_Retrieve(@RequestParam String token, @RequestParam String dwname,
			@RequestParam String args) {
		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			} else {
				// token 失效，返回错误信息
				WebDWControllerErrorRet ret = new WebDWControllerErrorRet(500, "Token 失效");
				return ret;
			}
		}

		DataWindowController webdwui = new DataWindowController();

		// step2. call SetDataObject
		try {
			webdwui.DW_SetDataObjectByName(username, dwname);
		} catch (WebDWAuthorizedException e) {
			e.printStackTrace();
			// token 失效，返回错误信息
			WebDWControllerErrorRet ret = new WebDWControllerErrorRet(500, e.getErrString());
			return ret;
		}
		System.out.println(webdwui.errString);

		System.out.println("begin to call retrieve function");
		// set DataBuffer
		try {
			webdwui.DW_Retrieve(username, args);
		} catch (WebDWAuthorizedException e) {
			e.printStackTrace();
			WebDWControllerErrorRet ret = new WebDWControllerErrorRet(500, e.getErrString());
			return ret;
		}
		// save datawindow object to cache
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
	@GetMapping(path = "/insertrow")
	public @ResponseBody WebDWControllerRet dw_f3_InsertRow(@RequestParam String token, @RequestParam String uuid) {

		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			} else {
				// token 失效，返回错误信息
				return new WebDWControllerErrorRet(500, "Token 失效");
			}
		}

		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		try {
			webdwui.DW_InsertRow(username, 0);
		} catch (WebDWException e) {
			e.printStackTrace();
			// token 失效，返回错误信息
			WebDWControllerErrorRet ret = new WebDWControllerErrorRet(500, e.getErrString());
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			WebDWControllerErrorRet ret = new WebDWControllerErrorRet(500, e.getMessage());
			return ret;
		}
		return webdwui.retObject;
	}

	@GetMapping(path = "/delete")
	public @ResponseBody WebDWControllerRet dw_f4_DeleteRow(@RequestParam String token, @RequestParam String uuid,
			@RequestParam int rowid) {
		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			} else {
				// token 失效，返回错误信息
				return new WebDWControllerErrorRet(500, "Token 无效");
			}
		}

		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		try {
			webdwui.DW_DeleteRow(username, rowid);
		} catch (WebDWAuthorizedException e) {
			e.printStackTrace();
			return new WebDWControllerErrorRet(500, e.getErrString());
		}
		return webdwui.retObject;
	}

	@GetMapping(path = "/setitem")
	public @ResponseBody WebDWControllerRet dw_f6_SetItem(@RequestParam String uuid, @RequestParam int rowid,
			@RequestParam int colid, @RequestParam String data) throws Exception {
		System.out.println("enter SetItem rowid:" + rowid);
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		webdwui.DW_SetItem(rowid, colid, data);
		return webdwui.retObject;
	}

	@GetMapping(path = "/update")
	public @ResponseBody WebDWControllerRet dw_f5_Update(@RequestParam String token, @RequestParam String uuid) {
		System.out.println("enter update...");

		// step1:get username
		String username = new WebDWDBUtil().getUserNameByToken(token);

		// 设置默认用户名，方便系统功能测试，默认用户名为guest
		if (username == "") {
			if (this.CONST_DEFAULT_USER_ALLOW) {
				username = this.CONST_DEFAULT_USERNAME;
			} else {
				return new WebDWControllerErrorRet(500, "Token 无效");
			}
		}

		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		try {
			webdwui.DW_Update(username);
		} catch (WebDWAuthorizedException e) {
			e.printStackTrace();
			// token 失效，返回错误信息
			return new WebDWControllerErrorRet(500, e.getErrString());
		}
		return webdwui.retObject;
	}
}
