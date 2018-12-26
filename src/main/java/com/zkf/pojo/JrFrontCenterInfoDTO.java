package com.zkf.pojo;

import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrPlan;
import com.zkf.mysql.entity.JrPlanExecuteResult;
import com.zkf.mysql.entity.JrTown;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/11/5.
 */
public class JrFrontCenterInfoDTO implements Serializable {

    private static final long serialVersionUID = 398302058976446912L;

    private List<JrQuerySingleUserResponseDTO> jrQuerySingleUserResponseDTOList;

    private List<JrPlan> jrPlanList;

    //特色活动
    private List<JrPlanExecuteResult> jrPlanExecuteResultList;

    //积分
    private List<JrTownIntegralDTO> jrTownIntegralDTOList;

    //镇积分
    private JrTownInfoDTO jrTownInfoDTO;

    //特色活动
    private List<JrFeatureResultDTO> jrFeatureResultDTO;

    public List<JrQuerySingleUserResponseDTO> getJrQuerySingleUserResponseDTOList() {
        return jrQuerySingleUserResponseDTOList;
    }

    public void setJrQuerySingleUserResponseDTOList(List<JrQuerySingleUserResponseDTO> jrQuerySingleUserResponseDTOList) {
        this.jrQuerySingleUserResponseDTOList = jrQuerySingleUserResponseDTOList;
    }

    public List<JrPlan> getJrPlanList() {
        return jrPlanList;
    }

    public void setJrPlanList(List<JrPlan> jrPlanList) {
        this.jrPlanList = jrPlanList;
    }

    public List<JrPlanExecuteResult> getJrPlanExecuteResultList() {
        return jrPlanExecuteResultList;
    }

    public void setJrPlanExecuteResultList(List<JrPlanExecuteResult> jrPlanExecuteResultList) {
        this.jrPlanExecuteResultList = jrPlanExecuteResultList;
    }

    public List<JrTownIntegralDTO> getJrTownIntegralDTOList() {
        return jrTownIntegralDTOList;
    }

    public void setJrTownIntegralDTOList(List<JrTownIntegralDTO> jrTownIntegralDTOList) {
        this.jrTownIntegralDTOList = jrTownIntegralDTOList;
    }

    public JrTownInfoDTO getJrTownInfoDTO() {
        return jrTownInfoDTO;
    }

    public void setJrTownInfoDTO(JrTownInfoDTO jrTownInfoDTO) {
        this.jrTownInfoDTO = jrTownInfoDTO;
    }

    public List<JrFeatureResultDTO> getJrFeatureResultDTO() {
        return jrFeatureResultDTO;
    }

    public void setJrFeatureResultDTO(List<JrFeatureResultDTO> jrFeatureResultDTO) {
        this.jrFeatureResultDTO = jrFeatureResultDTO;
    }
}
