package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2018/12/4.
 */
public class JrLeftPracticeDTO implements Serializable{
    private static final long serialVersionUID = -3605820508603742845L;

    private String centerName;

    private Long count;

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
