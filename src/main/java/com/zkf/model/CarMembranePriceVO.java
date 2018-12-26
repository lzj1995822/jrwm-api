package com.zkf.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarMembranePriceVO implements Serializable {

    private Integer carBrandId;

    private String carBrandName;

    private Integer carBrandModeId;

    private String carBrandModelName;

    private String carMembraneBeforeModel;

    private String carMembraneBeforeNo;

    private BigDecimal carMembraneBeforePrice;

    private String carMembraneAfterModel;

    private String carMembraneAfterNO;

    private BigDecimal carMembraneAfterPrice;

    private String carMembraneBodyModel;

    private String carMembraneBodyNO;

    private BigDecimal carMembraneBodyPrice;

    private String carMembraneWindowModel;

    private String carMembraneWindowNO;

    private BigDecimal carMembraneWindowPrice;
    private String carMembraneRearModel;

    private String carMembraneRearNO;

    private BigDecimal carMembraneRearPrice;

    private BigDecimal allPrice;

    private String serNo;

    public Integer getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Integer carBrandId) {
        this.carBrandId = carBrandId;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public Integer getCarBrandModeId() {
        return carBrandModeId;
    }

    public void setCarBrandModeId(Integer carBrandModeId) {
        this.carBrandModeId = carBrandModeId;
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

    public String getCarMembraneBeforeNo() {
        return carMembraneBeforeNo;
    }

    public void setCarMembraneBeforeNo(String carMembraneBeforeNo) {
        this.carMembraneBeforeNo = carMembraneBeforeNo;
    }

    public BigDecimal getCarMembraneBeforePrice() {
        return carMembraneBeforePrice;
    }

    public void setCarMembraneBeforePrice(BigDecimal carMembraneBeforePrice) {
        this.carMembraneBeforePrice = carMembraneBeforePrice;
    }

    public String getCarMembraneAfterModel() {
        return carMembraneAfterModel;
    }

    public void setCarMembraneAfterModel(String carMembraneAfterModel) {
        this.carMembraneAfterModel = carMembraneAfterModel;
    }

    public String getCarMembraneAfterNO() {
        return carMembraneAfterNO;
    }

    public void setCarMembraneAfterNO(String carMembraneAfterNO) {
        this.carMembraneAfterNO = carMembraneAfterNO;
    }

    public BigDecimal getCarMembraneAfterPrice() {
        return carMembraneAfterPrice;
    }

    public void setCarMembraneAfterPrice(BigDecimal carMembraneAfterPrice) {
        this.carMembraneAfterPrice = carMembraneAfterPrice;
    }

    public String getCarMembraneBodyModel() {
        return carMembraneBodyModel;
    }

    public void setCarMembraneBodyModel(String carMembraneBodyModel) {
        this.carMembraneBodyModel = carMembraneBodyModel;
    }

    public String getCarMembraneBodyNO() {
        return carMembraneBodyNO;
    }

    public void setCarMembraneBodyNO(String carMembraneBodyNO) {
        this.carMembraneBodyNO = carMembraneBodyNO;
    }

    public BigDecimal getCarMembraneBodyPrice() {
        return carMembraneBodyPrice;
    }

    public void setCarMembraneBodyPrice(BigDecimal carMembraneBodyPrice) {
        this.carMembraneBodyPrice = carMembraneBodyPrice;
    }

    public String getCarMembraneWindowModel() {
        return carMembraneWindowModel;
    }

    public void setCarMembraneWindowModel(String carMembraneWindowModel) {
        this.carMembraneWindowModel = carMembraneWindowModel;
    }

    public String getCarMembraneWindowNO() {
        return carMembraneWindowNO;
    }

    public void setCarMembraneWindowNO(String carMembraneWindowNO) {
        this.carMembraneWindowNO = carMembraneWindowNO;
    }

    public BigDecimal getCarMembraneWindowPrice() {
        return carMembraneWindowPrice;
    }

    public void setCarMembraneWindowPrice(BigDecimal carMembraneWindowPrice) {
        this.carMembraneWindowPrice = carMembraneWindowPrice;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    public String getCarMembraneRearModel() {
        return carMembraneRearModel;
    }

    public void setCarMembraneRearModel(String carMembraneRearModel) {
        this.carMembraneRearModel = carMembraneRearModel;
    }

    public String getCarMembraneRearNO() {
        return carMembraneRearNO;
    }

    public void setCarMembraneRearNO(String carMembraneRearNO) {
        this.carMembraneRearNO = carMembraneRearNO;
    }

    public BigDecimal getCarMembraneRearPrice() {
        return carMembraneRearPrice;
    }

    public void setCarMembraneRearPrice(BigDecimal carMembraneRearPrice) {
        this.carMembraneRearPrice = carMembraneRearPrice;
    }

    public String getSerNo() {
        return serNo;
    }

    public void setSerNo(String serNo) {
        this.serNo = serNo;
    }
}
