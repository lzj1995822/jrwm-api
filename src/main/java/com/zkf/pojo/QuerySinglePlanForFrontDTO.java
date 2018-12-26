package com.zkf.pojo;

import com.common.utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zkf on 2018/12/12.
 */
public class QuerySinglePlanForFrontDTO implements Serializable{
    private static final long serialVersionUID = -2966809264042634363L;

    private String accessory;

    private String planName;

    private String planContent;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Timestamp expireTime;

    private String centerType;

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public String getCenterType() {
        return centerType;
    }

    public void setCenterType(String centerType) {
        this.centerType = centerType;
    }
}
