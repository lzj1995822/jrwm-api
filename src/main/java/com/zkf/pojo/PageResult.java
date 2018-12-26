package com.zkf.pojo;

import com.zkf.page.Paginator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/12/19.
 */
public class PageResult<T> implements Serializable{

    private static final long serialVersionUID = -3527043784813087306L;
    private List<T> list;

    private Paginator paginator;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
