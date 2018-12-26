package com.zkf.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceSVO implements Serializable {

    private String location;

    private BigDecimal privce;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrivce() {
        return privce;
    }

    public void setPrivce(BigDecimal privce) {
        this.privce = privce;
    }
}
