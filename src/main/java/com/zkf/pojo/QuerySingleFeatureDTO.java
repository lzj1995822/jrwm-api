package com.zkf.pojo;

import com.common.utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zkf on 2018/12/19.
 */
public class QuerySingleFeatureDTO implements Serializable{
    private static final long serialVersionUID = -2649313528176930028L;

    private String planName;

    private String planContent;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Timestamp expireTime;

    private String accessory;

    private String centerName;

    private String resultContent;

    private String practiceName;

    private List<String> resultPic;

    private String resultVideo;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public List<String> getResultPic() {
        return resultPic;
    }

    public void setResultPic(List<String> resultPic) {
        this.resultPic = resultPic;
    }

    public String getResultVideo() {
        return resultVideo;
    }

    public void setResultVideo(String resultVideo) {
        this.resultVideo = resultVideo;
    }
}
