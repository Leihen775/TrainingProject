package pers.train.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 主页控制器
 * @author mingshan
 *
 */

@Controller
@RequestMapping("/admin")
public class IndexController {

	/**
	 * 进入后台主页
	 * @param map
	 * @return
	 */
	@RequestMapping("/index")
	public String goIndex(ModelMap map){
	
		return "admin/index";
	}
	
	/**
	 * 欢迎界面
	 * @return
	 */
	@RequestMapping("/welcome")
	public String goMain(){
		return "admin/welcome";
	}
	
}
