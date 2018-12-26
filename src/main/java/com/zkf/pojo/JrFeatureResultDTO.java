package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/11/15.
 */
public class JrFeatureResultDTO implements Serializable{
    private static final long serialVersionUID = -5659086574125223258L;

    private String planName;

    private String townName;

    private String countryName;

    private String pic;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
