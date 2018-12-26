package com.zkf.pojo;


import java.io.Serializable;

public class EduChildRequestDTO implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    private String token;

    private String name;

    private String headPic;

    private Integer gender;

    private Integer age;

    private Integer gradeS;

    private Integer gradeT;

    private Integer status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Integer getGradeS() {
        return gradeS;
    }

    public void setGradeS(Integer gradeS) {
        this.gradeS = gradeS;
    }

    public Integer getGradeT() {
        return gradeT;
    }

    public void setGradeT(Integer gradeT) {
        this.gradeT = gradeT;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
