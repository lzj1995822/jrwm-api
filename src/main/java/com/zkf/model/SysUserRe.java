package com.zkf.model;


import com.zkf.mysql.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Administrator
 *
 */
public class SysUserRe extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6409487431848233815L;

    private String userId;

	private Integer id;

    private String userPwdConfirm;

    private String userPwd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwdConfirm() {
		return userPwdConfirm;
	}

	public void setUserPwdConfirm(String userPwdConfirm) {
		this.userPwdConfirm = userPwdConfirm;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
