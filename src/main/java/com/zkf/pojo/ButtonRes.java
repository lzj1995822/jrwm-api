package com.zkf.pojo;

import java.io.Serializable;

/**
 * Created by zkf on 2017/12/24.
 */
public class ButtonRes implements Serializable{

    private static final long serialVersionUID = 7639573374219254379L;

    private String buttonName;

    private Boolean isShow;

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }
}
