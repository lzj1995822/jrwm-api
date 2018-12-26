package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2017/12/9.
 */
public class SysUserReq implements Serializable{

    private static final long serialVersionUID = 7639573374219254379L;

    private int page;

    private int size;
    private String passenger;
    private String orderId;
    private String orderChangeId;
    private String launchTimeBeginTime;
    private String launchTimeEndTime;
    private String name;
    private String title;
    private String carBrandName;
    private String carBrandModelName;
    private String carMembraneModelType;
    private String carMembraneBeforeModel;
    private String carMembraneAfterModel;
    private String carMembraneBodyModel;
    private String carMembraneWindowModel;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderChangeId() {
        return orderChangeId;
    }

    public void setOrderChangeId(String orderChangeId) {
        this.orderChangeId = orderChangeId;
    }

    public String getLaunchTimeBeginTime() {
        return launchTimeBeginTime;
    }

    public void setLaunchTimeBeginTime(String launchTimeBeginTime) {
        this.launchTimeBeginTime = launchTimeBeginTime;
    }

    public String getLaunchTimeEndTime() {
        return launchTimeEndTime;
    }

    public void setLaunchTimeEndTime(String launchTimeEndTime) {
        this.launchTimeEndTime = launchTimeEndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public String getCarBrandModelName() {
        return carBrandModelName;
    }

    public void setCarBrandModelName(String carBrandModelName) {
        this.carBrandModelName = carBrandModelName;
    }

    public String getCarMembraneBeforeModel() {
        return carMembraneBeforeModel;
    }

    public void setCarMembraneBeforeModel(String carMembraneBeforeModel) {
        this.carMembraneBeforeModel = carMembraneBeforeModel;
    }

    public String getCarMembraneAfterModel() {
        return carMembraneAfterModel;
    }

    public void setCarMembraneAfterModel(String carMembraneAfterModel) {
        this.carMembraneAfterModel = carMembraneAfterModel;
    }

    public String getCarMembraneBodyModel() {
        return carMembraneBodyModel;
    }

    public void setCarMembraneBodyModel(String carMembraneBodyModel) {
        this.carMembraneBodyModel = carMembraneBodyModel;
    }

    public String getCarMembraneWindowModel() {
        return carMembraneWindowModel;
    }

    public void setCarMembraneWindowModel(String carMembraneWindowModel) {
        this.carMembraneWindowModel = carMembraneWindowModel;
    }

    public String getCarMembraneModelType() {
        return carMembraneModelType;
    }

    public void setCarMembraneModelType(String carMembraneModelType) {
        this.carMembraneModelType = carMembraneModelType;
    }
}
