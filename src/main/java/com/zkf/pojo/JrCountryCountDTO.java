package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/12/4.
 */
public class JrCountryCountDTO implements Serializable {
    private static final long serialVersionUID = 5646033115866095720L;

    private String townName;

    private Long countryCount;

    private String lon;

    private String lat;

    private Long townId;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Long getCountryCount() {
        return countryCount;
    }

    public void setCountryCount(Long countryCount) {
        this.countryCount = countryCount;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Long getTownId() {
        return townId;
    }

    public void setTownId(Long townId) {
        this.townId = townId;
    }
}
