package com.zkf.mysql.entity;


import com.common.utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "jr_plan")
public class JrPlan extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "expire_time")
    private Timestamp expireTime;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "content")
    private String content;

    @Column(name = "plan_status")
    private Integer planStatus;

    @Column(name = "complete")
    private Integer complete;

    @Column(name = "check_status")
    private Integer checkStatus;

    @Column(name = "select_type")
    private Integer selectType;

    @Column(name = "accessory")
    private String accessory;

    @Column(name = "center_id")
    private Long centerId;

    @Column(name = "integral")
    private Integer integral;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "town_room_id")
    private Long townRoomId;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getTownRoomId() {
        return townRoomId;
    }

    public void setTownRoomId(Long townRoomId) {
        this.townRoomId = townRoomId;
    }
}
