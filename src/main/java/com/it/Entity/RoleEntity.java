package com.it.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_Role")
public class RoleEntity {

	@Id
	private String  RoleId;
	private String RoleName;
	private String RoleDescription;
	private String RoleStatus;
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public String getRoleDescription() {
		return RoleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		RoleDescription = roleDescription;
	}
	public String getRoleStatus() {
		return RoleStatus;
	}
	public void setRoleStatus(String roleStatus) {
		RoleStatus = roleStatus;
	}
	
	
	
}
