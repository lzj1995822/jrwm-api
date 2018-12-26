package com.zkf.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/11/11.
 */
public class JrOfficeFrontDTO implements Serializable{
    private static final long serialVersionUID = -8915325049035017395L;

    private String name;

    private String content;

    private List<JrQuerySingleUserResponseDTO> jrQuerySingleUserResponseDTOList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<JrQuerySingleUserResponseDTO> getJrQuerySingleUserResponseDTOList() {
        return jrQuerySingleUserResponseDTOList;
    }

    public void setJrQuerySingleUserResponseDTOList(List<JrQuerySingleUserResponseDTO> jrQuerySingleUserResponseDTOList) {
        this.jrQuerySingleUserResponseDTOList = jrQuerySingleUserResponseDTOList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
