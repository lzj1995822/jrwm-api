package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/12/10.
 */
public class PublishPlanDTO implements Serializable {
    private static final long serialVersionUID = -7614088653326854535L;

    private String name;

    private String content;

    private String accessory;

    private Integer integral;

    private String expireTime;

    private Long centerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }
}
