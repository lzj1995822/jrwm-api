package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/10/14.
 */
public class JrRegisterDTO implements Serializable{
    private static final long serialVersionUID = 6436842891584808363L;

    private Integer type;

    private String userId;

    private String userPwd;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
