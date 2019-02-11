package hello.webdw.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webdw.controller.DataWindowController;
import com.webdw.common.MyInt;
import com.webdw.controller.CWebDWMemCache;
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
@RequestMapping(path = "/") // This means URL's start with /demo (after Application path)
public class WebDWController {
	/**
	 * test only function,show a string value;
	 * 
	 * @return
	 */
	@GetMapping(path = "/")
	public @ResponseBody String getAllUsers() {
		// This returns a JSON or XML with the users
		String a = "Welcome to webdw's world!";
		return a;
	}

	/**
	 * setdataobject function
	 * 
	 * @param dwname
	 *            ���ݴ��ڶ���
	 * @return
	 */
	@GetMapping(path = "/setdataobject")
	public @ResponseBody WebDWControllerRet SetDataobject(@RequestParam String dwname) throws Exception {
		DataWindowController webdwui = new DataWindowController();
		WebDWControllerRet ret = new WebDWControllerRet();

		ArrayList retList = new ArrayList();
		MyJPanel targPict = new MyJPanel("targPict");

		webdwui.DW_SetDataObjectByName(retList, targPict, dwname);
		System.out.println(webdwui.errString);
		System.out.println(retList.size());

		ret.uuid = webdwui.uuid;
		ret.uiobjList = retList;

		CWebDWMemCache.saveParentDW(webdwui);
		return ret;
	}

	/**
	 * retrieve function,
	 * 
	 * @param dwname
	 * 
	 * @return
	 */
	@GetMapping(path = "/retrieve")
	public @ResponseBody WebDWControllerRet Retrieve(@RequestParam String dwname) throws Exception {
		DataWindowController webdwui = new DataWindowController();
		WebDWControllerRet ret = new WebDWControllerRet();

		ArrayList retList = new ArrayList();
		MyJPanel targPict = new MyJPanel("targPict");

		webdwui.DW_SetDataObjectByName(retList, targPict, dwname);
		System.out.println(webdwui.errString);
		System.out.println(retList.size());

		System.out.println("begin to call retrieve function");
		// set DataBuffer
		webdwui.DW_Retrieve("");

		CWebDWMemCache.saveParentDW(webdwui);
		// System.out.println("rowcount:" + webdwui.DW_RowCount());
		ret.uuid = webdwui.uuid;
		ret.uiobjList = retList;
		return ret;
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

		ArrayList retList = new ArrayList();
		MyJPanel targPict = new MyJPanel("targPict");
		webdwui.targetControls = retList;
		webdwui.targetPict = targPict;

		webdwui.DW_InsertRow(0);
		webdwui.DrawDW();
		WebDWControllerRet ret = new WebDWControllerRet();

		ret.uuid = uuid;
		ret.uiobjList = webdwui.targetControls;
		return ret;
	}

	@GetMapping(path = "/delete")
	public @ResponseBody WebDWControllerRet Delete(@RequestParam String uuid, @RequestParam int rowid)
			throws Exception {
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		ArrayList retList = new ArrayList();
		MyJPanel targPict = new MyJPanel("targPict");
		webdwui.targetControls = retList;
		webdwui.targetPict = targPict;

		webdwui.DW_DeleteRow(rowid);
		webdwui.DrawDW();
		WebDWControllerRet ret = new WebDWControllerRet();

		ret.uuid = uuid;
		ret.uiobjList = webdwui.targetControls;
		return ret;
	}

	@GetMapping(path = "/setdata")
	public @ResponseBody WebDWControllerRet SetData(@RequestParam String uuid, @RequestParam int rowid,
			@RequestParam int colid, @RequestParam String data) throws Exception {
		System.out.println("enter SetData rowid:" + rowid);
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		ArrayList retList = new ArrayList();
		MyJPanel targPict = new MyJPanel("targPict");
		webdwui.targetControls = retList;
		webdwui.targetPict = targPict;

		webdwui.DW_SetItem(rowid, colid, data);
		// webdwui.DrawDW();
		WebDWControllerRet ret = new WebDWControllerRet();

		ret.uuid = uuid;
		ret.uiobjList = webdwui.targetControls;
		return ret;
	}

	@GetMapping(path = "/update")
	public @ResponseBody WebDWControllerRet Update(@RequestParam String uuid) throws Exception {
		System.out.println("enter update...");
		DataWindowController webdwui = new DataWindowController();
		webdwui = CWebDWMemCache.readParentDW(uuid);

		ArrayList retList = new ArrayList();
		MyJPanel targPict = new MyJPanel("targPict");
		webdwui.targetControls = retList;
		webdwui.targetPict = targPict;

		webdwui.DW_Update();
		webdwui.DrawDW();
		WebDWControllerRet ret = new WebDWControllerRet();

		ret.uuid = uuid;
		ret.uiobjList = webdwui.targetControls;
		return ret;
	}
}
