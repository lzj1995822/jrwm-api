package com.zkf.pojo;

import com.zkf.page.Paginator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/12/12.
 */
public class MyExecutePlanResponseDTO implements Serializable{
    private static final long serialVersionUID = -3948528453160049491L;

    private List<MyExecutePlanDTO> myExecutePlanDTOS;

    private Paginator paginator;

    public List<MyExecutePlanDTO> getMyExecutePlanDTOS() {
        return myExecutePlanDTOS;
    }

    public void setMyExecutePlanDTOS(List<MyExecutePlanDTO> myExecutePlanDTOS) {
        this.myExecutePlanDTOS = myExecutePlanDTOS;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
