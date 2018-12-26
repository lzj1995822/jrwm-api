package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "wf_role_mapping_func")
public class RoleMappingFunc extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Column(name = "func_id", nullable = false)
    private String funcId;

	@Column(name = "button", nullable = false)
	private String button;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}
}
