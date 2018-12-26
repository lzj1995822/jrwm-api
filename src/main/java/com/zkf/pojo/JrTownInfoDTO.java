package com.zkf.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/11/15.
 */
public class JrTownInfoDTO implements Serializable{

    private static final long serialVersionUID = -563662574561637068L;
    private Integer integralTotal;

    private Integer rank;

    private List<JrTownIntegralDTO> jrTownIntegralDTOList;

    public Integer getIntegralTotal() {
        return integralTotal;
    }

    public void setIntegralTotal(Integer integralTotal) {
        this.integralTotal = integralTotal;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<JrTownIntegralDTO> getJrTownIntegralDTOList() {
        return jrTownIntegralDTOList;
    }

    public void setJrTownIntegralDTOList(List<JrTownIntegralDTO> jrTownIntegralDTOList) {
        this.jrTownIntegralDTOList = jrTownIntegralDTOList;
    }
}
