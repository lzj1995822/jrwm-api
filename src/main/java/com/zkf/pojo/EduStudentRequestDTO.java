package com.zkf.pojo;


import java.io.Serializable;

public class EduStudentRequestDTO implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    private String token;


    private String headPic;

    private String name;

    private Integer gender;

    private Integer age;

    private String email;

    private Integer area;

    private Integer school;

    private Integer campus;

    private String major;

    private Integer isTeacherCertification;

    private Integer isCet;

    private Integer isZhikai;

    private Integer prize;

    private Integer experience;

    private String memo;

    private Integer workType;

    private String selfAssessment;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
