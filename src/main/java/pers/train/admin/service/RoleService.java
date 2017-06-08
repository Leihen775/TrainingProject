package pers.train.admin.service;

import java.util.List;

import pers.train.admin.po.SecurityRole;
import pers.train.common.base.service.BaseService;


/**
 * 角色
 * @author mingshan
 *
 */
public interface RoleService extends BaseService<SecurityRole>{
	//批量删除
	int deleteBatch(SecurityRole securityRole); 
	
	//模糊查询
	List<SecurityRole> selectRoleByToken(String token);
}
