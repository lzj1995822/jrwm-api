package com.zkf.model;

import java.io.Serializable;
import java.util.List;

public class WarrantyCadeVO implements Serializable {

    private Integer storesId;

    private String storesName;

    private String storesAddress;

    private String constructionType;

    private String constructionPerson;

    private String constructionTime;

    private String sales;

    private String warrantyCard;

    private String salesPhone;

    protected String customerName;

    protected String customerSex;

    protected String customerBirthday;

    protected String reservedPhone;

    protected String customerPhone;

    protected String provinces;

    protected String city;

    protected String county;

    protected String address;

    protected String zipCode;

    protected String email;

    protected String carBrand;

    protected String carModels;

    protected String carIdentification;

    protected String carNumber;

    protected String qualityPrice;

    protected String describers;

    protected String createUserId;

    protected String createTime;

    protected String updateUserId;

    protected String updateTime;

    protected String warrantyValidity;

    //车膜信息
    protected List<CarMembranePriceVO> carMembranePriceList;

    protected String carMembranePriceNO;

    protected Boolean isLifetime;
    protected String fixPhone;

    private String validityNum;

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    public String getConstructionTime() {
        return constructionTime;
    }

    public void setConstructionTime(String constructionTime) {
        this.constructionTime = constructionTime;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getWarrantyCard() {
        return warrantyCard;
    }

    public void setWarrantyCard(String warrantyCard) {
        this.warrantyCard = warrantyCard;
    }

    public String getSalesPhone() {
        return salesPhone;
    }

    public void setSalesPhone(String salesPhone) {
        this.salesPhone = salesPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getReservedPhone() {
        return reservedPhone;
    }

    public void setReservedPhone(String reservedPhone) {
        this.reservedPhone = reservedPhone;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModels() {
        return carModels;
    }

    public void setCarModels(String carModels) {
        this.carModels = carModels;
    }

    public String getCarIdentification() {
        return carIdentification;
    }

    public void setCarIdentification(String carIdentification) {
        this.carIdentification = carIdentification;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getQualityPrice() {
        return qualityPrice;
    }

    public void setQualityPrice(String qualityPrice) {
        this.qualityPrice = qualityPrice;
    }

    public String getDescribers() {
        return describers;
    }

    public void setDescribers(String describers) {
        this.describers = describers;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWarrantyValidity() {
        return warrantyValidity;
    }

    public void setWarrantyValidity(String warrantyValidity) {
        this.warrantyValidity = warrantyValidity;
    }

    public List<CarMembranePriceVO> getCarMembranePriceList() {
        return carMembranePriceList;
    }

    public void setCarMembranePriceList(List<CarMembranePriceVO> carMembranePriceList) {
        this.carMembranePriceList = carMembranePriceList;
    }

    public String getCarMembranePriceNO() {
        return carMembranePriceNO;
    }

    public void setCarMembranePriceNO(String carMembranePriceNO) {
        this.carMembranePriceNO = carMembranePriceNO;
    }

    public Boolean getLifetime() {
        return isLifetime;
    }

    public void setLifetime(Boolean lifetime) {
        isLifetime = lifetime;
    }

    public String getStoresAddress() {
        return storesAddress;
    }

    public void setStoresAddress(String storesAddress) {
        this.storesAddress = storesAddress;
    }

    public String getFixPhone() {
        return fixPhone;
    }

    public void setFixPhone(String fixPhone) {
        this.fixPhone = fixPhone;
    }

    public String getConstructionPerson() {
        return constructionPerson;
    }

    public void setConstructionPerson(String constructionPerson) {
        this.constructionPerson = constructionPerson;
    }

    public String getValidityNum() {
        return validityNum;
    }

    public void setValidityNum(String validityNum) {
        this.validityNum = validityNum;
    }
}
