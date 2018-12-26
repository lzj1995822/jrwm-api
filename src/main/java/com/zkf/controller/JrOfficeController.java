package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrOffice;
import com.zkf.mysql.entity.JrTown;
import com.zkf.mysql.entity.JrTownRoom;
import com.zkf.mysql.repository.*;
import com.zkf.pojo.JrOrganizationResponseDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class JrOfficeController {

    private static final Logger logger = LoggerFactory.getLogger(JrOfficeController.class);

    @Resource
    private JrPlanRepo jrPlanRepo;

    @Resource
    private JrUserRepo jrUserRepo;

    @Resource
    private JrPlanExecuteResultRepo jrPlanExecuteResultRepo;

    @Resource
    private JrCountryRepo jrCountryRepo;

    @Resource
    private JrTownRepo jrTownRepo;

    @Resource
    private JrCenterRepo jrCenterRepo;

    @Resource
    private JrTownRoomRepo jrTownRoomRepo;

    @Resource
    private JrOfficeRepo jrOfficeRepo;

    @PostMapping("/addOffice")
    @ResponseBody
    public VResult<?> addOffice(@RequestBody JrOffice jrOffice, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrOffice.setCreateUserId(userId);
            jrOffice.setStatus(1);
            jrOfficeRepo.save(jrOffice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/officeContentHave")
    @ResponseBody
    public VResult<?> officeContentHave(Long id, HttpServletRequest request) {
        VResult<Boolean> vResult = new VResult<>(false);
        try {
            JrOffice jrOfficeOri = jrOfficeRepo.findById(id);
            if(StringUtils.isBlank(jrOfficeOri.getContent())){
                vResult.setContent(false);
            } else {
                vResult.setContent(true);
            }
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/updateOfficeName")
    @ResponseBody
    public VResult<?> updateOfficeName(Long id, String name, HttpServletRequest request) {
        VResult<Boolean> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrOffice jrOffice = jrOfficeRepo.findById(id);
            jrOffice.setName(name);
            jrOffice.setUpdateUserId(userId);
            jrOfficeRepo.save(jrOffice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/updateOffice")
    @ResponseBody
    public VResult<?> updateOffice(@RequestBody JrOffice jrOffice, HttpServletRequest request) {
        VResult<Boolean> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrOffice jrOfficeOri = jrOfficeRepo.findById(jrOffice.getId());
            jrOffice.setName(jrOfficeOri.getName());
            jrOffice.setUpdateUserId(userId);
            jrOffice.setCreateTime(jrOfficeOri.getCreateTime());
            jrOffice.setCreateUserId(jrOfficeOri.getCreateUserId());
            jrOfficeRepo.save(jrOffice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/addOfficeContent")
    @ResponseBody
    public VResult<?> addOfficeContent(@RequestBody JrOffice jrOffice, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            JrOffice jrOfficeOri = jrOfficeRepo.findById(jrOffice.getId());
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrOffice.setName(jrOfficeOri.getName());
            jrOffice.setUpdateUserId(userId);
            jrOffice.setCreateTime(jrOfficeOri.getCreateTime());
            jrOffice.setCreateUserId(jrOfficeOri.getCreateUserId());
            jrOffice.setStatus(1);
            jrOfficeRepo.save(jrOffice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryOffice")
    @ResponseBody
    public VResult<?> queryOffice(HttpServletRequest request) {
        VResult<JrOffice> vResult = new VResult<>(false);
        try {
            List<JrOffice> jrOffices = jrOfficeRepo.findAll();
            vResult.setContents(jrOffices);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryOfficeById")
    @ResponseBody
    public VResult<?> queryOfficeById(HttpServletRequest request, Long id) {
        VResult<JrOffice> vResult = new VResult<>(false);
        try {
            JrOffice jrOffice = jrOfficeRepo.findById(id);
            vResult.setContent(jrOffice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/delOffice")
    @ResponseBody
    public VResult<?> delOffice(Long officeId, HttpServletRequest request) {
        VResult<JrCenter> vResult = new VResult<>(false);
        try {
            JrOffice jrOffice = jrOfficeRepo.findById(officeId);
            jrOfficeRepo.delete(jrOffice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

}
