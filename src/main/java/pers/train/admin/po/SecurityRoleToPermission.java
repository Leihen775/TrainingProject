package pers.train.admin.po;



/**
 * 角色与权限对应实体类
 * @author mingshan
 *
 */

public class SecurityRoleToPermission {


	private int id;
	

	private int roleId;
	

	private int permissionId;
	
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
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	
	
}
