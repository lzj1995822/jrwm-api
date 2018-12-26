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
@Table(name = "tb_sys_user")
public class SysUser extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "role_id", nullable = true)
    private String roleId;

    @Column(name = "modules", nullable = true)
    private String modules;

    @Column(name = "expire_date", nullable = true)
    private Timestamp expireDate;

    @Column(name = "last_login_time", nullable = true)
    private Timestamp lastLoginTime;

    @Column(name = "last_login_ip", nullable = true)
    private String lastLoginIp;

    @Column(name = "memo", nullable = true)
    private String memo;

	@Column(name = "police_station_type", nullable = true)
	private String policeStationType;

	@Column(name = "areaCode")
	private String areaCode;

	@Column(name = "stores_id", nullable = true)
	private Integer storesId;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}


	public Timestamp getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPoliceStationType() {
		return policeStationType;
	}

	public void setPoliceStationType(String policeStationType) {
		this.policeStationType = policeStationType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getStoresId() {
		return storesId;
	}

	public void setStoresId(Integer storesId) {
		this.storesId = storesId;
	}
}
