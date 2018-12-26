package com.zkf.pojo;

import com.zkf.page.Paginator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/12/11.
 */
public class CheckForTownRoomResponseDTO implements Serializable{
    private static final long serialVersionUID = 6135148875763129813L;

    private List<CheckForTownRoomDTO> checkForTownRoomDTOS;

    private Paginator paginator;

    public List<CheckForTownRoomDTO> getCheckForTownRoomDTOS() {
        return checkForTownRoomDTOS;
    }

    public void setCheckForTownRoomDTOS(List<CheckForTownRoomDTO> checkForTownRoomDTOS) {
        this.checkForTownRoomDTOS = checkForTownRoomDTOS;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
