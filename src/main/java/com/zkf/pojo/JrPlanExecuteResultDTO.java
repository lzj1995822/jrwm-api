package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/11/8.
 */
public class JrPlanExecuteResultDTO implements Serializable{
    private static final long serialVersionUID = 8990034714644705103L;

    private Long planId;

    private String resultPic;

    private String resultContent;

    private Long practiceId;

    private String resultVideo;

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

    public Long getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Long practiceId) {
        this.practiceId = practiceId;
    }

    public String getResultVideo() {
        return resultVideo;
    }

    public void setResultVideo(String resultVideo) {
        this.resultVideo = resultVideo;
    }
}
