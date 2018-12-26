package com.zkf.mysql.repository;

import java.io.Serializable;

/**
 * Created by zkf on 2018/11/29.
 */
public class JrQueryUserDTO implements Serializable{
    private static final long serialVersionUID = -476129583618768737L;

    private Long centerId;

    private Long countryId;

    private Long townId;

    private Long townRoomId;

    private Long jrId;

    private Long officeId;

    private Integer pageNum;

    private Integer pageSize;

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getTownId() {
        return townId;
    }

    public void setTownId(Long townId) {
        this.townId = townId;
    }

    public Long getTownRoomId() {
        return townRoomId;
    }

    public void setTownRoomId(Long townRoomId) {
        this.townRoomId = townRoomId;
    }

    public Long getJrId() {
        return jrId;
    }

    public void setJrId(Long jrId) {
        this.jrId = jrId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
