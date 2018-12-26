package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrInformation;
import com.zkf.mysql.entity.JrTown;
import com.zkf.mysql.entity.JrTownRoom;
import com.zkf.mysql.repository.*;
import com.zkf.pojo.JrOrganizationResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class JrInformationController {

    private static final Logger logger = LoggerFactory.getLogger(JrInformationController.class);

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
    private JrInformationRepo jrInformationRepo;

    @PostMapping("/addInformation")
    @ResponseBody
    public VResult<?> addCenter(@RequestBody JrInformation jrInformation, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrInformation.setCreateUserId(userId);
            jrInformationRepo.save(jrInformation);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //通知公告
    @PostMapping("/queryInformation")
    @ResponseBody
    public VResult<?> queryInformation(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<Page> vResult = new VResult<>(false);
        try {
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Page<JrInformation> jrInformations = jrInformationRepo.findAll(pageAble);
            vResult.setContent(jrInformations);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/delInformation")
    @ResponseBody
    public VResult<?> queryInformation(Long informationId, HttpServletRequest request) {
        VResult<JrInformation> vResult = new VResult<>(false);
        try {
            JrInformation jrInformation = jrInformationRepo.findById(informationId);
            jrInformationRepo.delete(jrInformation);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/updateInformation")
    @ResponseBody
    public VResult<?> updateInformation(@RequestBody JrInformation jrInformation, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            JrInformation jrInformationOri = jrInformationRepo.findById(jrInformation.getId());
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrInformation.setUpdateUserId(userId);
            jrInformation.setCreateTime(jrInformationOri.getCreateTime());
            jrInformation.setCreateUserId(jrInformationOri.getCreateUserId());
            jrInformationRepo.save(jrInformation);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/querySingleInformation")
    @ResponseBody
    public VResult<?> querySingleInformation(Long id, HttpServletRequest request) {
        VResult<JrInformation> vResult = new VResult<>(false);
        try {
            JrInformation jrInformation = jrInformationRepo.findById(id);
            vResult.setContent(jrInformation);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

}
