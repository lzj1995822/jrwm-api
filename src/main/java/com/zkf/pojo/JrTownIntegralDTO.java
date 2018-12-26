package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/11/13.
 */
public class JrTownIntegralDTO implements Serializable{
    private static final long serialVersionUID = -7654746505805916476L;

    private String name;

    private Integer integral;

    private Long townId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Long getTownId() {
        return townId;
    }

    public void setTownId(Long townId) {
        this.townId = townId;
    }
}
