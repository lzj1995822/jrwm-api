package com.zkf.pojo;

import com.zkf.page.Paginator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/12/13.
 */
public class AfterResultResponseDTO implements Serializable{
    private static final long serialVersionUID = -6279152667605590366L;

    private List<AfterResultDTO> afterResultDTOS;

    private Paginator paginator;

    public List<AfterResultDTO> getAfterResultDTOS() {
        return afterResultDTOS;
    }

    public void setAfterResultDTOS(List<AfterResultDTO> afterResultDTOS) {
        this.afterResultDTOS = afterResultDTOS;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
