package pers.train.admin.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import pers.train.admin.po.SecurityUser;
import pers.train.common.base.service.BaseService;


public interface UserService extends BaseService<SecurityUser>{

	/**根据userName获取角色**/
	Set<String> getRoles(String userName);
	
	/**获取权限**/
	Set<String> getPermissions(String userName);
	
	/**模糊查询用户**/
	List<SecurityUser> selectUserByToken(String username);
	
	/**查询所有的学生**/
	List<SecurityUser> selectStudent(Map map);
	
	/**查询所有的学生 数量   -分页**/
	int findStudentCount();
	
	/**查询所有用户 -数量**/
    int findUserCount();
    
    /**查询所有用户 -分页**/
  	List<SecurityUser> selectUserList(Map map);
  	
    /**批量删除**/
  	int deleteBatch(SecurityUser securityUser);
  	
    /**模糊查询所有用户**/
  	List<SecurityUser> selectAllUserByToken(String token);
}
