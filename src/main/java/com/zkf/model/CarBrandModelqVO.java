package com.zkf.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

public class CarBrandModelqVO implements Serializable {

    private Integer carBrandId;

    private String carBrandName;

    private Integer carBrandModeId;

    private String carBrandModelName;

    private String carMembraneBeforeModel;

    private BigDecimal carMembraneBeforePrice;

    private String carMembraneAfterModel;

    private BigDecimal carMembraneAfterPrice;

    private String carMembraneBodyModel;

    private BigDecimal carMembraneBodyPrice;

    private String carMembraneWindowModel;

    private BigDecimal carMembraneWindowPrice;

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

    public BigDecimal getCarMembraneWindowPrice() {
        return carMembraneWindowPrice;
    }

    public void setCarMembraneWindowPrice(BigDecimal carMembraneWindowPrice) {
        this.carMembraneWindowPrice = carMembraneWindowPrice;
    }

    public String getSerNo() {
        return serNo;
    }

    public void setSerNo(String serNo) {
        this.serNo = serNo;
    }
}
