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
@Table(name = "wf_func")
public class Func extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "func_id", nullable = false)
    private String funcId;

	@Column(name = "module_id", nullable = false)
	private String moduleId;

    @Column(name = "func_name", nullable = false)
    private String funcName;

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}
