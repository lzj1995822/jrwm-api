package com.zkf.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zkf on 2018/10/14.
 */
public class MainInfoResponseDTO implements Serializable{

    private static final long serialVersionUID = 3976967417845461332L;

    private List<String> carouselList;

    private List<EduInformationDTO> eduInformationDTOList;

    public List<String> getCarouselList() {
        return carouselList;
    }

    public void setCarouselList(List<String> carouselList) {
        this.carouselList = carouselList;
    }

    public List<EduInformationDTO> getEduInformationDTOList() {
        return eduInformationDTOList;
    }

    public void setEduInformationDTOList(List<EduInformationDTO> eduInformationDTOList) {
        this.eduInformationDTOList = eduInformationDTOList;
    }
}
