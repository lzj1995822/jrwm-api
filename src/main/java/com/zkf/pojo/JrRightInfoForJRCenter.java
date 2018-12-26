package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/11/15.
 */
public class JrRightInfoForJRCenter implements Serializable {
    private static final long serialVersionUID = -4299595718271226839L;

    private Long planResultTotal;

    private Long passPlanResultTotal;

    private Long radio;

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

    public Long getRadio() {
        return radio;
    }

    public void setRadio(Long radio) {
        this.radio = radio;
    }
}
