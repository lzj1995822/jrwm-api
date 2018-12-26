package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "edu_organization")
public class EduOrganization extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "name")
    private String name;

    @Column(name = "head_pic")
    private String headPic;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "area")
    private Integer area;

    @Column(name = "address")
    private String address;

    @Column(name = "train_item")
    private String trainItem;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "status")
    private Integer status;

    @Column(name = "full_time")
    private Integer fullTime;

    @Column(name = "part_time")
    private Integer partTime;

    @Column(name = "business_license")
    private String businessLicense;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTrainItem() {
        return trainItem;
    }

    public void setTrainItem(String trainItem) {
        this.trainItem = trainItem;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFullTime() {
        return fullTime;
    }

    public void setFullTime(Integer fullTime) {
        this.fullTime = fullTime;
    }

    public Integer getPartTime() {
        return partTime;
    }

    public void setPartTime(Integer partTime) {
        this.partTime = partTime;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }
}
