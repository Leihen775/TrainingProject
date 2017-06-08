package pers.train.admin.po;


/**
 *  权限 实体类
 * @author mingshan
 *
 */

public class SecurityPermission {


	
	private int id;
	

	private String permissionName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
}
