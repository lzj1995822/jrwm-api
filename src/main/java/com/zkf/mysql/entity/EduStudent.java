package com.zkf.mysql.entity;


import com.common.utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "edu_student")
public class EduStudent extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "head_pic")
    private String headPic;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "area")
    private Integer area;

    @Column(name = "school")
    private Integer school;

    @Column(name = "campus")
    private Integer campus;

    @Column(name = "major")
    private String major;

    @Column(name = "is_teacher_certification")
    private Integer isTeacherCertification;

    @Column(name = "is_cet")
    private Integer isCet;

    @Column(name = "is_zhikai")
    private Integer isZhikai;

    @Column(name = "prize")
    private Integer prize;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "platform_prize")
    private Integer platformPrize;

    @Column(name = "memo")
    private String memo;

    @Column(name = "work_type")
    private Integer workType;

    @Column(name = "self_assessment")
    private String selfAssessment;

    @Column(name = "status")
    private Integer status;

    @Column(name = "star_level")
    private Integer starLevel;

    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "stick")
    private Timestamp stick;

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

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

    public Integer getCampus() {
        return campus;
    }

    public void setCampus(Integer campus) {
        this.campus = campus;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getIsTeacherCertification() {
        return isTeacherCertification;
    }

    public void setIsTeacherCertification(Integer isTeacherCertification) {
        this.isTeacherCertification = isTeacherCertification;
    }

    public Integer getIsCet() {
        return isCet;
    }

    public void setIsCet(Integer isCet) {
        this.isCet = isCet;
    }

    public Integer getIsZhikai() {
        return isZhikai;
    }

    public void setIsZhikai(Integer isZhikai) {
        this.isZhikai = isZhikai;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public String getSelfAssessment() {
        return selfAssessment;
    }

    public void setSelfAssessment(String selfAssessment) {
        this.selfAssessment = selfAssessment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getPlatformPrize() {
        return platformPrize;
    }

    public void setPlatformPrize(Integer platformPrize) {
        this.platformPrize = platformPrize;
    }

    public Integer getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public Timestamp getStick() {
        return stick;
    }

    public void setStick(Timestamp stick) {
        this.stick = stick;
    }
}
