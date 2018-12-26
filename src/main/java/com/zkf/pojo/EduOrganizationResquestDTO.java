package com.zkf.pojo;


import java.io.Serializable;

public class EduOrganizationResquestDTO implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    private String token;

    private String mobile;

    private String name;

    private String headPic;

    private String contactPerson;

    private Integer area;

    private String address;

    private String trainItem;

    private String introduction;

    private Integer fullTime;

    private Integer partTime;

    private String businessLicense;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
