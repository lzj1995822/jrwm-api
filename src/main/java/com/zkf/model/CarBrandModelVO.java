package com.zkf.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

public class CarBrandModelVO implements Serializable {

    private Integer carBrandId;

    private String carBrandName;

    private String carBrandModelName;

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

    public String getCarBrandModelName() {
        return carBrandModelName;
    }

    public void setCarBrandModelName(String carBrandModelName) {
        this.carBrandModelName = carBrandModelName;
    }
}
