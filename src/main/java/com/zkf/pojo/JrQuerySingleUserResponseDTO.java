package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/11/9.
 */
public class JrQuerySingleUserResponseDTO implements Serializable{
    private static final long serialVersionUID = 6531337514670150650L;

    private String positionName;

    private String userName;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
