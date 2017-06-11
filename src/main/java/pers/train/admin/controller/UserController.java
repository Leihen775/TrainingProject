package pers.train.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pers.train.admin.po.SecurityUser;
import pers.train.admin.po.SecurityUserToRole;
import pers.train.admin.service.UserService;
import pers.train.admin.service.UserToRoleService;
import pers.train.common.util.CrypographyUtil;

/**
 * 用户控制器
 * @author mingshan
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private UserToRoleService userToRoleService;
	
	
/*	@RequestMapping("/index")
	public String index(ModelMap map){
		
		List<SecurityUser> list = userService.findAll();
		
		map.put("list", list);
		
		return "list";
	}*/
	
	/**
	 * 显示登录注册界面
	 * @return
	 */
	@RequestMapping("/login")
	public String showLogin(){
		return "admin/login";
	}
	
	/**
	 * 认证未通过或者权限不足
	 * @return
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		return "admin/authority/unauthorized";
	}
	
	/**
	 * 执行登录
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 */
	@RequestMapping("/dealLogin")
	public void dealLogin(String userName,String password,HttpServletRequest request,HttpServletResponse response){
		Subject currentUser = SecurityUtils.getSubject();
		
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json");
		PrintWriter out = null;
		String flag="1";
		
		if (!currentUser.isAuthenticated()) {
        	// 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            // rememberme
            //token.setRememberMe(true);
            try {
            	System.out.println("1. " + token.hashCode());
            	// 执行登录. 
                currentUser.login(token);
               
        		Set<String> role = userService.getRoles(userName);
        		System.out.println("role-"+role);
        		SecurityUser user = new SecurityUser();
        	    user.setUserName(userName);
        		user = (SecurityUser) userService.selectByUniqueFiled(user);
        		//获取uid
        		int uid = user.getId();
        		HttpSession session = request.getSession();
        		//将用户信息保存到session 中
                session.setAttribute("role", role);
                session.setAttribute("uid", uid);
            } 
            // 所有认证时异常的父类. 
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            	System.out.println("登录失败: " + ae.getMessage());
            	request.setAttribute("error", "用户名或密码错误");
            	flag="0";
            }
        }
		

		try {
			out=response.getWriter();
			out.write(flag);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	/**
	 * 保存  用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String saveUser(SecurityUser user){
		try{
			
		
			//获取原始密码
			String pw = user.getPassword();
			//获取用户名
			String userName = user.getUserName();
			//密码用MD5加密，并且需要加点盐
			user.setSalt(userName);
			Object object = CrypographyUtil.MD5(userName, pw, userName);
			
			user.setPassword(object.toString());
			//将用户状态置为1
			user.setStatus(1);
			userService.insert(user);
			
			System.out.println("UID==="+user.getId());
			//将用户的角色置为游客
			SecurityUserToRole userToRole = new SecurityUserToRole();
			userToRole.setRoleId(2);
			userToRole.setUserId(user.getId());
			userToRoleService.insert(userToRole);
			
			return "1";
		}catch(RuntimeException e){
			e.printStackTrace();
			return "0";
		}
		
	}
	
	/**
	 * 检测用户
	 * @param userName
	 * @return
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public String checkUser(String userName){
		SecurityUser user = new SecurityUser();
		user.setUserName(userName);
		user = (SecurityUser) userService.selectByUniqueFiled(user);
      
        String result = ""; 
        if(user == null){
        	result = "1";
        }else{
        	result = "0";
        }
		return result;
	}
	
	/**
	 * 检测邮箱
	 * @param email
	 * @return
	 */
	@RequestMapping("/checkEmail")
	@ResponseBody
	public String checkEmail(String email){
		SecurityUser user = new SecurityUser();
		user.setEmail(email);
		user = (SecurityUser) userService.selectByUniqueFiled(user);
      
        String result = ""; 
        if(user == null){
        	result = "1";
        }else{
        	result = "0";
        }
		return result;
	}
	
	
	
	/**
	 * 
	* @Title: updatwePassword
	* @Description: TODO 更新密码
	* @param @param userName
	* @param @param password
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updatwePassword(int uid,String userName,String email, @RequestParam(required=false) String oldPassword, @RequestParam(required=false) String newPassword){
		String result = "";
		SecurityUser user = new SecurityUser();
		user = (SecurityUser) userService.findById(uid);
		//先获取原始密码
		String oldpw = user.getPassword();
		System.out.println(oldpw);
		//获取原始用户名
		String oldUsername = user.getUserName();
		
		
		//此时判断一下密码是否为空
		if("".equals(oldPassword) && "".equals(newPassword)){
			//密码为空  不更新密码这一项
			 user.setUserName(userName);
			 user.setEmail(email);
			 user.setSalt(userName);
			 //将密码置为空 ，不执行更改
			 user.setPassword("");
			
		}else{
			//需要将传过来的比对的密码进行加密
			Object object = CrypographyUtil.MD5(oldUsername, oldPassword, oldUsername);
			String oldMD5Pw = object.toString();
			if(oldMD5Pw.equals(oldpw)){
				//此时密码比对正确，进行更改密码和用户名操作
		
				 //将密码进行MD5加密保存
				 Object object2 = CrypographyUtil.MD5(userName, newPassword, userName);
				 String MD5Pw = object2.toString();
			     user.setUserName(userName);
				 user.setEmail(email);
				 user.setPassword(MD5Pw);
				 user.setSalt(userName);
				
			}else{
				result = "0";
			}	
		}
		
	    //最后再执行
		try{
			 //执行更新操作
			 userService.update(user);
			 return "1";
		 }catch(RuntimeException e){
			 result = "2";
		 }
		return result;
	}
	
	/**
	 * 查询用户信息
	 * @param id
	 */
	
	@RequestMapping("/findUserInfo")
	public void findUserInfo(String userName,HttpServletResponse response){
		PrintWriter out = null;
		//获取权限列表
		SecurityUser user = new SecurityUser();
		user.setUserName(userName);
		user = userService.selectByUniqueFiled(user);
		 //调用fastjson生成json信息
		String json = JSON.toJSONString(user, true);
		System.out.println(json);
		response.setContentType("application/json");
		try {
			out=response.getWriter();
			out.write(json);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		//session置为空
		HttpSession session = request.getSession();
		session.removeAttribute("uid");
		session.removeAttribute("role");
		return "redirect:login";
	}
}
