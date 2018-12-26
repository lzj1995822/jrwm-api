package com.zkf.pojo;

import com.common.utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zkf on 2018/12/20.
 */
public class QuerySinglePlanResDTO implements Serializable{
    private static final long serialVersionUID = 1101094059311556507L;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Timestamp expireTime;

    private String name;

    private String accessory;

    private String content;

    private String centerName;

    private Integer integral;

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

}
