package pers.train.admin.po;


/**
 * 用户与角色关系
 * @author mingshan
 *
 */

public class SecurityUserToRole {


	private int id;
	
	private int roleId;
	
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
