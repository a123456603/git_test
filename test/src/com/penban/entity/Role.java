package com.penban.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {

	private Integer roleId;
	private String roleName;
	private int displayId;
	private Set<User> users = new HashSet<User>();

	public Role() {

	}

	public Role(Integer roleId, String roleName, int displayId) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.displayId = displayId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getDisplayId() {
		return displayId;
	}

	public void setDisplayId(int displayId) {
		this.displayId = displayId;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
