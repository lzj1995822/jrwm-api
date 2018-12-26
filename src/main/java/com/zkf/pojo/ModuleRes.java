package com.zkf.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2017/12/24.
 */
public class ModuleRes implements Serializable{

    private static final long serialVersionUID = 7639573374219254379L;

    private String moduleName;

    private List<FuncRes> funcResList;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<FuncRes> getFuncResList() {
        return funcResList;
    }

    public void setFuncResList(List<FuncRes> funcResList) {
        this.funcResList = funcResList;
    }
}
