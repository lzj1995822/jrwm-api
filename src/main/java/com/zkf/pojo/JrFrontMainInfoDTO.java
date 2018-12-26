package com.zkf.pojo;

import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrTown;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/11/5.
 */
public class JrFrontMainInfoDTO implements Serializable{

    private static final long serialVersionUID = 398302058976446912L;
    private List<JrCenter> jrCenterList;

    private List<JrTown> jrTownList;

    private List<JrQuerySingleUserResponseDTO> jrQuerySingleUserResponseDTOList;

    private List<JrOfficeFrontDTO> jrOfficeFrontDTOList;

    //积分
    private List<JrTownIntegralDTO> jrTownIntegralDTOList;

    //特色活动比例
    private Integer featureRatio;

    public List<JrCenter> getJrCenterList() {
        return jrCenterList;
    }

    public void setJrCenterList(List<JrCenter> jrCenterList) {
        this.jrCenterList = jrCenterList;
    }

    public List<JrTown> getJrTownList() {
        return jrTownList;
    }

    public void setJrTownList(List<JrTown> jrTownList) {
        this.jrTownList = jrTownList;
    }

    public List<JrQuerySingleUserResponseDTO> getJrQuerySingleUserResponseDTOList() {
        return jrQuerySingleUserResponseDTOList;
    }

    public void setJrQuerySingleUserResponseDTOList(List<JrQuerySingleUserResponseDTO> jrQuerySingleUserResponseDTOList) {
        this.jrQuerySingleUserResponseDTOList = jrQuerySingleUserResponseDTOList;
    }

    public List<JrOfficeFrontDTO> getJrOfficeFrontDTOList() {
        return jrOfficeFrontDTOList;
    }

    public void setJrOfficeFrontDTOList(List<JrOfficeFrontDTO> jrOfficeFrontDTOList) {
        this.jrOfficeFrontDTOList = jrOfficeFrontDTOList;
    }

    public List<JrTownIntegralDTO> getJrTownIntegralDTOList() {
        return jrTownIntegralDTOList;
    }

    public void setJrTownIntegralDTOList(List<JrTownIntegralDTO> jrTownIntegralDTOList) {
        this.jrTownIntegralDTOList = jrTownIntegralDTOList;
    }

    public Integer getFeatureRatio() {
        return featureRatio;
    }

    public void setFeatureRatio(Integer featureRatio) {
        this.featureRatio = featureRatio;
    }
}
