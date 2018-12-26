package com.zkf.mysql.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "jr_plan_execute_result")
public class JrPlanExecuteResult extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6409487431848233815L;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "result_pic")
    private String resultPic;

    @Column(name = "result_video")
    private String resultVideo;

    @Column(name = "result_content")
    private String resultContent;

    @Column(name = "feature")
    private Integer feature;

    @Column(name = "check_status")
    private Integer checkStatus;

    @Column(name = "check_town_room_id")
    private Long checkTownRoomId;

    @Column(name = "check_center_id")
    private Long checkCenterId;

    @Column(name = "execute_status")
    private Integer executeStatus;

    @Column(name = "practice_id")
    private Long practiceId;

    @Column(name = "result_type")
    private Integer resultType;

    @Column(name = "complete_time")
    private Timestamp completeTime;

    @Column(name = "memo")
    private String memo;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getResultPic() {
        return resultPic;
    }

    public void setResultPic(String resultPic) {
        this.resultPic = resultPic;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    public Integer getFeature() {
        return feature;
    }

    public void setFeature(Integer feature) {
        this.feature = feature;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }


    public Long getCheckCenterId() {
        return checkCenterId;
    }

    public void setCheckCenterId(Long checkCenterId) {
        this.checkCenterId = checkCenterId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Integer getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(Integer executeStatus) {
        this.executeStatus = executeStatus;
    }

    public Long getCheckTownRoomId() {
        return checkTownRoomId;
    }

    public void setCheckTownRoomId(Long checkTownRoomId) {
        this.checkTownRoomId = checkTownRoomId;
    }

    public Long getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Long practiceId) {
        this.practiceId = practiceId;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public Timestamp getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Timestamp completeTime) {
        this.completeTime = completeTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getResultVideo() {
        return resultVideo;
    }

    public void setResultVideo(String resultVideo) {
        this.resultVideo = resultVideo;
    }
}
