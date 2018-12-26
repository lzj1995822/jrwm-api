package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "jr_town_practice")
public class JrTownPractice extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "name")
    private String name;

    @Column(name = "town_id")
    private Long townId;

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
}
