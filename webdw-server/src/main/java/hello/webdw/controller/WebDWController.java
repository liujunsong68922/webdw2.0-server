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
import com.webdw.controller.CWebDWMemCache;
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
@RequestMapping(path = "/") // This means URL's start with /demo (after Application path)
public class WebDWController {
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
	public @ResponseBody WebDWControllerRet SetDataobject(@RequestParam String dwname) {

		DataWindowController webdwui = new DataWindowController();
		webdwui.DW_SetDataObjectByName(dwname);

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
	public @ResponseBody WebDWControllerRet Retrieve(@RequestParam String dwname,
			@RequestParam String args) throws Exception {
		DataWindowController webdwui = new DataWindowController();

		webdwui.DW_SetDataObjectByName(dwname);
		System.out.println(webdwui.errString);

		System.out.println("begin to call retrieve function");
		// set DataBuffer
		webdwui.DW_Retrieve(args);
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
	public @ResponseBody WebDWControllerRet Insert(@RequestParam String uuid) throws Exception {
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);


		webdwui.DW_InsertRow(0);
		return webdwui.retObject;
	}

	@GetMapping(path = "/delete")
	public @ResponseBody WebDWControllerRet Delete(@RequestParam String uuid, @RequestParam int rowid)
			throws Exception {
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		webdwui.DW_DeleteRow(rowid);

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
	public @ResponseBody WebDWControllerRet Update(@RequestParam String uuid) throws Exception {
		System.out.println("enter update...");
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		webdwui.DW_Update();
		return webdwui.retObject;
	}
}
