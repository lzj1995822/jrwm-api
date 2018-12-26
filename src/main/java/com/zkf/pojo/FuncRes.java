package com.zkf.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2017/12/24.
 */
public class FuncRes implements Serializable{

    private static final long serialVersionUID = 7639573374219254379L;

    private String funcName;

    private Boolean isShow;

    private List<ButtonRes> buttonResList;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public List<ButtonRes> getButtonResList() {
        return buttonResList;
    }

    public void setButtonResList(List<ButtonRes> buttonResList) {
        this.buttonResList = buttonResList;
    }
}
