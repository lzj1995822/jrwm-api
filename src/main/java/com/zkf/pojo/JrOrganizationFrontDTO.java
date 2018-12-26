package com.zkf.pojo;

import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrOffice;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/11/5.
 */
public class JrOrganizationFrontDTO implements Serializable{
    private static final long serialVersionUID = 6682548550707744116L;

    private List<JrOffice> jrOfficeList;

    private List<JrCenter> jrCenterList;

    public List<JrOffice> getJrOfficeList() {
        return jrOfficeList;
    }

    public void setJrOfficeList(List<JrOffice> jrOfficeList) {
        this.jrOfficeList = jrOfficeList;
    }

    public List<JrCenter> getJrCenterList() {
        return jrCenterList;
    }

    public void setJrCenterList(List<JrCenter> jrCenterList) {
        this.jrCenterList = jrCenterList;
    }
}
