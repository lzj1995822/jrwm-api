package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/12/12.
 */
public class AfterResultDTO implements Serializable{
    private static final long serialVersionUID = 6166635571554137725L;

    private String countryName;

    private String status;

    private Long resultId;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }
}
