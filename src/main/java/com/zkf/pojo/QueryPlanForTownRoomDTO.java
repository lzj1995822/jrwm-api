package com.zkf.pojo;

import com.common.utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zkf on 2018/12/12.
 */
public class QueryPlanForTownRoomDTO implements Serializable {
    private static final long serialVersionUID = -7048249779486868548L;

    private Long resultId;

    private String planName;

    private String centerName;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Timestamp completeTime;

    private String planAccessory;

    private String planContent;

    private String resultContent;

    private String resultAccessory;

    private String practiceName;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Timestamp getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Timestamp completeTime) {
        this.completeTime = completeTime;
    }

    public String getPlanAccessory() {
        return planAccessory;
    }

    public void setPlanAccessory(String planAccessory) {
        this.planAccessory = planAccessory;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    public String getResultAccessory() {
        return resultAccessory;
    }

    public void setResultAccessory(String resultAccessory) {
        this.resultAccessory = resultAccessory;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }
}
