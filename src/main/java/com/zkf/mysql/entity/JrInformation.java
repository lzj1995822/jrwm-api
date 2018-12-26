package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "jr_information")
public class JrInformation extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "title")
    private String title;

    @Column(name = "organization_type")
    private Integer organizationType;

    @Column(name = "content")
    private String content;

    @Column(name = "accessory")
    private String accessory;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(Integer organizationType) {
        this.organizationType = organizationType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }
}
