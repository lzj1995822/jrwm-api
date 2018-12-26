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
@Table(name = "wf_module")
public class Module extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "module_id", nullable = false)
    private String moduleId;

    @Column(name = "module_name", nullable = false)
    private String moduleName;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
