package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "wf_role")
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "role_id", nullable = true)
    private String roleId;

    @Column(name = "role_name", nullable = true)
    private String roleName;

    @Column(name = "role_desc", nullable = true)
    private String roleDesc;

    @Column(name = "modules", nullable = true)
    private String modules;

    @Column(name = "funcs", nullable = true)
    private String funcs;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getFuncs() {
		return funcs;
	}

	public void setFuncs(String funcs) {
		this.funcs = funcs;
	}
}
