package hello.webdw.testcontroller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webdw.controller.DataWindowController;
import com.webdw.common.MyInt;
import com.webdw.common.exception.WebDWException;
import com.webdw.common.util.SQLStringReplaceUtil;
import com.webdw.controller.CWebDWMemCache;
import com.webdw.model.CWebDW;
import com.webdw.model.dwsyntax.WebDWSyntax;
import com.webdw.view.ui.container.MyJPanel;

/**
 * WebDW Controller
 * 
 * @author Liu JunSong(liujunsong@aliyun.com)
 * @Date:2018/12/8
 * @version:1.0 访问路径/webdw/
 * 
 */

@Controller // This means that this class is a Controller
@RequestMapping(path = "/string") // This means URL's start with /demo (after Application path)
public class TestStringController {

	@GetMapping(path = "/test")
	public @ResponseBody String Test1() {
		SQLStringReplaceUtil util = new SQLStringReplaceUtil();
		String argvalues = "name=aaa&code=bbb";
		String arg="code";
		return util.getValue(argvalues, arg);
	}

	@GetMapping(path = "/test2")
	public @ResponseBody String Test2() {
		SQLStringReplaceUtil util = new SQLStringReplaceUtil();
		String instring = "select count(*) from name = @name";
		String argvalues = "name=aaa&code=bbb";
		String[] args= {"name","code"};
		return util.Replace(instring, args,argvalues);
	}

}
