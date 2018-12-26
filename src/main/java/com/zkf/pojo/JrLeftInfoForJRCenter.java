package com.zkf.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/11/15.
 */
public class JrLeftInfoForJRCenter implements Serializable {
    private static final long serialVersionUID = -4299595718271226839L;

    private Long planResultTotal;

    private Long passPlanResultTotal;

    private Long selfPlanTotal;

    private Long featureRadio;

    private Long commonRadio;

    private List<JrLeftPracticeDTO> practiceDTO;

    public Long getPlanResultTotal() {
        return planResultTotal;
    }

    public void setPlanResultTotal(Long planResultTotal) {
        this.planResultTotal = planResultTotal;
    }

    public Long getPassPlanResultTotal() {
        return passPlanResultTotal;
    }

    public void setPassPlanResultTotal(Long passPlanResultTotal) {
        this.passPlanResultTotal = passPlanResultTotal;
    }

    public Long getSelfPlanTotal() {
        return selfPlanTotal;
    }

    public void setSelfPlanTotal(Long selfPlanTotal) {
        this.selfPlanTotal = selfPlanTotal;
    }

    public Long getFeatureRadio() {
        return featureRadio;
    }

    public void setFeatureRadio(Long featureRadio) {
        this.featureRadio = featureRadio;
    }

    public Long getCommonRadio() {
        return commonRadio;
    }

    public void setCommonRadio(Long commonRadio) {
        this.commonRadio = commonRadio;
    }

    public List<JrLeftPracticeDTO> getPracticeDTO() {
        return practiceDTO;
    }

    public void setPracticeDTO(List<JrLeftPracticeDTO> practiceDTO) {
        this.practiceDTO = practiceDTO;
    }
}
