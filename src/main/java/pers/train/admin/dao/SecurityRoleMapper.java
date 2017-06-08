package pers.train.admin.dao;

import java.util.List;

import pers.train.admin.po.SecurityRole;
import pers.train.common.base.dao.BaseMapper;


/**
 * 角色
 * @author mingshan
 *
 */
public interface SecurityRoleMapper extends BaseMapper<SecurityRole>{

	//批量删除
	int deleteBatch(SecurityRole securityRole); 
	
	//模糊查询
	List<SecurityRole> selectRoleByToken(String token);
}
