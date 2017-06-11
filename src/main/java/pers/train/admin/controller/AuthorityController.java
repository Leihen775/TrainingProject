package pers.train.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pers.train.admin.po.SecurityResources;
import pers.train.admin.po.SecurityRole;
import pers.train.admin.po.SecurityUser;
import pers.train.admin.po.SecurityUserToRole;
import pers.train.admin.service.ResourcesService;
import pers.train.admin.service.RoleService;
import pers.train.admin.service.UserService;
import pers.train.admin.service.UserToRoleService;
import pers.train.common.base.service.PageHelperService;
import pers.train.common.config.PageSizeConfig;



/**
 * 权限管理控制器
 * @author mingshan
 *
 */
@Controller
@RequestMapping("/admin/authority")
public class AuthorityController{
    
    @Autowired
    private RoleService roleService;
   
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserToRoleService userToRoleService;
    
    @Autowired
    private ResourcesService resourcesService;
    
    
    @Autowired
    private PageHelperService pageHelperService;
    
    private int pageSize=PageSizeConfig.ADMIN_PAGE_SIZE;
    
    /**
     * 角色列表
     * @param map
     * @return
     */
    @RequestMapping("/roleList")
	public String showRoleList(ModelMap map){
		
		//获取角色列表
		ArrayList<SecurityRole> roles = (ArrayList<SecurityRole>)roleService.findAll();
		
		map.put("roles", roles);
		return "admin/authority/role-list";
	}
	
    /**
     * 显示 角色添加页面
     * @return
     */
    @RequestMapping("/showAddRole")
	public String showAddRole(){
		return "admin/authority/add-role";
	}
	
    /**
     * 保存角色信息
     * @param role
     * @return
     */
    @RequestMapping("/saveRole")
    @ResponseBody
    public String saveRole(SecurityRole role){
    	int result = roleService.insert(role);
    	return result + "";
    }
    
    /**
	 * 
	* @Title: deleteRole
	* @Description: TODO 删除角色
	* @param @param ids
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteRole(String ids){
		try{
		    //将字符串转为字符串数组
	        String[] idArray = ids.split(","); 
	        //将字符串数组转为整形数组
	        Integer[] iid = new Integer[idArray.length];
	        for(int i = 0;i < iid.length; i++){
	        	iid[i] = Integer.parseInt(idArray[i]);
	        }
	        //将数组转为list
	        List<Integer> idList = new ArrayList<Integer>();	
	        Collections.addAll(idList, iid);
	        
	        SecurityRole role = new SecurityRole();
	        role.setIds(idList);
	        //执行删除语句
	        roleService.deleteBatch(role);
	        return "1";
		}catch(RuntimeException e){
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 模糊查询角色
	 * @param token
	 * @param response
	 */
	@RequestMapping("/findRoleByName")
	public  void findRoleByName(String token,HttpServletResponse response){
		PrintWriter out = null;
		//获取权限列表
		ArrayList<SecurityRole> roles = (ArrayList<SecurityRole>) roleService.selectRoleByToken(token);
		 //调用fastjson生成json信息
		String json = JSON.toJSONString(roles, true);
		System.out.println(json);
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(json);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	
	}
	
	
	/**
	 * 分配角色页面
	 * @param p
	 * @param map
	 * @return
	 */
	@RequestMapping("/allocation")
	public String showAllotRole(int p,ModelMap map){
		 
		   String sp = p + "";
		   if("".equals(sp)){
				p = 1;
		   }
		   //当前的url
		   String url = "./allocation?p=";
			
		   //获取总记录量
		   int count = userService.findUserCount();
		   //计算偏移量
		   int position = (p-1)*pageSize;
		   
		   HashMap<String,Object> uMap = new HashMap<String,Object>();
		   uMap.put("position", position);
		   uMap.put("pageSize",pageSize);
		   
		   ArrayList<SecurityUser> user = (ArrayList<SecurityUser>)userService.selectUserList(uMap);
		 
		   //获取封装好的分页导航数据
	       String toolBar = pageHelperService.createToolBar(count,pageSize, url, p);		

	       //获取角色列表
		   ArrayList<SecurityRole> roles = (ArrayList<SecurityRole>)roleService.findAll();
			
		   map.put("roles", roles);
	       map.put("user", user);
	       map.put("toolBar", toolBar);

	       return "admin/authority/user-list";
	}
	
	/**
	 * 修改用户角色
	 * @param userToRole
	 * @return
	 */
	@RequestMapping("/updateUserRole")
	@ResponseBody
	public String  updateUserRole(SecurityUserToRole userToRole){
		int r;
		
		//更新数据
		int result = userToRoleService.update(userToRole);
		if(result>0){
			r = 1;
		}else{
			r = 0;
		}		
		
		return r + "";
	}
	
	
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(String ids){
		try{
		    //将字符串转为字符串数组
	        String[] idArray = ids.split(","); 
	        //将字符串数组转为整形数组
	        Integer[] iid = new Integer[idArray.length];
	        for(int i = 0; i< iid.length; i++){
	        	iid[i] = Integer.parseInt(idArray[i]);
	        }
	        //将数组转为list
	        List<Integer> idList = new ArrayList<Integer>();	
	        Collections.addAll(idList, iid);
	        
	        SecurityUser user = new SecurityUser();
	        user.setIds(idList);
	        //执行删除语句
	        userService.deleteBatch(user);
	        return "1";
		}catch(RuntimeException e){
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 获取资源列表
	 * @param map
	 * @return
	 */
	@RequestMapping("/resources")
	public String resources(ModelMap map){
		ArrayList<SecurityResources> resources = (ArrayList<SecurityResources>) resourcesService.findAll();
	
		map.put("resources", resources);
		return "admin/authority/resources-list";
	}
	
	/**
	 * 模糊查询用户
	 * @param token
	 * @param response
	 */
	@RequestMapping("/findUserByName")
	public  void findUserByName(String token,HttpServletResponse response){
		PrintWriter out = null;
		//获取权限列表
		ArrayList<SecurityUser> roles = (ArrayList<SecurityUser>)  userService.selectAllUserByToken(token);
		 //调用fastjson生成json信息
		String json = JSON.toJSONString(roles, true);
		System.out.println(json);
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(json);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	
    
}
