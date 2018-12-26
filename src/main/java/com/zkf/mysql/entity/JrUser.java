package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "jr_user")
public class JrUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "modules")
    private String modules;

    @Column(name = "expire_date")
    private Timestamp expireDate;

    @Column(name = "type")
    private Integer type;

    @Column(name = "center_id")
    private Long centerId;

    @Column(name = "center_practice_id")
    private Long centerPracticeId;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "country_practice_id")
    private Long countryPracticeId;

    @Column(name = "town_id")
    private Long townId;

    @Column(name = "town_practice_id")
    private Long townPracticeId;

    @Column(name = "town_room_id")
    private Long townRoomId;

    @Column(name = "jr_id")
    private Long jrId;

    @Column(name = "office_id")
    private Long officeId;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "position")
    private Integer position;

    @Column(name = "mobile")
    private String mobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Long getCenterPracticeId() {
        return centerPracticeId;
    }

    public void setCenterPracticeId(Long centerPracticeId) {
        this.centerPracticeId = centerPracticeId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCountryPracticeId() {
        return countryPracticeId;
    }

    public void setCountryPracticeId(Long countryPracticeId) {
        this.countryPracticeId = countryPracticeId;
    }

    public Long getTownId() {
        return townId;
    }

    public void setTownId(Long townId) {
        this.townId = townId;
    }

    public Long getTownPracticeId() {
        return townPracticeId;
    }

    public void setTownPracticeId(Long townPracticeId) {
        this.townPracticeId = townPracticeId;
    }

    public Long getTownRoomId() {
        return townRoomId;
    }

    public void setTownRoomId(Long townRoomId) {
        this.townRoomId = townRoomId;
    }

    public Long getJrId() {
        return jrId;
    }

    public void setJrId(Long jrId) {
        this.jrId = jrId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
