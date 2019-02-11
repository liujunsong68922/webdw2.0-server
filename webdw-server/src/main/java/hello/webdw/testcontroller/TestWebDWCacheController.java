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
@RequestMapping(path = "/memcache") // This means URL's start with /demo (after Application path)
public class TestWebDWCacheController {

	/**
	 * test function,to get dwdefine string,show it on front-end;
	 * 
	 * @param dwname
	 * 
	 * @return
	 */
	@GetMapping(path = "/all")
	public @ResponseBody HashMap GetAllCacheObject() {
		return new CWebDWMemCache().getObjStore();
	}

}
