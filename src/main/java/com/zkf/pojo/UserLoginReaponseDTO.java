package com.zkf.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zkf on 2018/10/15.
 */
public class UserLoginReaponseDTO implements Serializable{
    private static final long serialVersionUID = 3335965929803844443L;

    private String token;

    private Integer userType;

    private String userName;

    private Date lastLogin;

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
