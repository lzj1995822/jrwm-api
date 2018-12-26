package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "edu_child")
public class EduChild extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "patriarch_id")
    private Long patriarchId;

    @Column(name = "name")
    private String name;

    @Column(name = "head_pic")
    private String headPic;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "grade_s")
    private Integer gradeS;

    @Column(name = "grade_t")
    private Integer gradeT;

    @Column(name = "status")
    private Integer status;

    public Long getPatriarchId() {
        return patriarchId;
    }

    public void setPatriarchId(Long patriarchId) {
        this.patriarchId = patriarchId;
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
