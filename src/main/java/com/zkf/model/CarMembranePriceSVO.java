package com.zkf.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarMembranePriceSVO implements Serializable {

    private String location;

    private String carBrandName;

    private String carBrandModelName;

    private String carMembraneModel;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getCarMembraneModel() {
        return carMembraneModel;
    }

    public void setCarMembraneModel(String carMembraneModel) {
        this.carMembraneModel = carMembraneModel;
    }
}
