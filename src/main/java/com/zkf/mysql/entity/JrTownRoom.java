package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "jr_town_room")
public class JrTownRoom extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;


    @Column(name = "name")
    private String name;

    @Column(name = "town_id")
    private Long townId;

    @Column(name = "center_id")
    private Long centerId;

    @Column(name = "lon")
    private String lon;

    @Column(name = "lat")
    private String lat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTownId() {
        return townId;
    }

    public void setTownId(Long townId) {
        this.townId = townId;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
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
}
