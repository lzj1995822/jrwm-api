package com.zkf.pojo;

import com.zkf.mysql.entity.JrUser;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/11/9.
 */
public class JrQueryUserResponseDTO implements Serializable{
    private static final long serialVersionUID = -2367267411283083802L;

    private Page<JrUser> jrUserList;

    private List<JrQuerySingleUserResponseDTO> jrQuerySingleUserResponseDTOList;

    public Page<JrUser> getJrUserList() {
        return jrUserList;
    }

    public void setJrUserList(Page<JrUser> jrUserList) {
        this.jrUserList = jrUserList;
    }

    public List<JrQuerySingleUserResponseDTO> getJrQuerySingleUserResponseDTOList() {
        return jrQuerySingleUserResponseDTOList;
    }

    public void setJrQuerySingleUserResponseDTOList(List<JrQuerySingleUserResponseDTO> jrQuerySingleUserResponseDTOList) {
        this.jrQuerySingleUserResponseDTOList = jrQuerySingleUserResponseDTOList;
    }
}
