package hello.webdw.testcontroller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webdw.controller.DataWindowController;
import com.webdw.exception.WebDWException;
import com.webdw.common.MyInt;
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
@RequestMapping(path = "/testCWebDW") // This means URL's start with /demo (after Application path)
public class TestCWebDWController {

	/**
	 * test function,to get dwdefine string,show it on front-end;
	 * 
	 * @param dwname
	 * 
	 * @return
	 */
	@GetMapping(path = "/createbydwname")
	public @ResponseBody CWebDW GetDWDefine(@RequestParam String dwname) {
		CWebDW pp = new CWebDW();
		try {
			int iret = pp.CreateByDwName(dwname);
			System.out.println(iret);
		} catch (WebDWException e) {
			e.printStackTrace();
			System.out.println(e.getErrString());
			pp.errString = e.getErrString();
		}
		return pp;
	}

}
