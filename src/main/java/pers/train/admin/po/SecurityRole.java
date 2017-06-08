package pers.train.admin.po;

import java.util.List;

/**
 * 角色
 * @author mingshan
 *
 */
public class SecurityRole{


	private int id;
	

	private String roleName;


	private String roleCode;
	
	//批量删除用
	private List<Integer> ids;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	
	
}
