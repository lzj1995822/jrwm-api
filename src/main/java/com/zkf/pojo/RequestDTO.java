package com.zkf.pojo;

/**
 * Created by zkf on 2018/8/11.
 */
public class RequestDTO {
    private static final long serialVersionUID = 7639573374219254379L;

    private int page;

    private int size;

    private String condition;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
