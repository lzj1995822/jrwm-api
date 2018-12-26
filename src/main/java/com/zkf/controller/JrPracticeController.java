package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
import com.zkf.mysql.repository.*;
import com.zkf.pojo.JrOrganizationResponseDTO;
import com.zkf.pojo.QuerySingleFeatureDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JrPracticeController {

    private static final Logger logger = LoggerFactory.getLogger(JrPracticeController.class);

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
    private JrPracticeRepo jrPracticeRepo;

    @PostMapping("/queryCenterPractice")
    @ResponseBody
    public VResult<?> queryCenterPractice(HttpServletRequest request, Long centerId) {
        VResult<JrPractice> vResult = new VResult<>(false);
        try {
            List<JrPractice> jrPractices = jrPracticeRepo.findByCenterId(centerId);
            vResult.setContents(jrPractices);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryTownRoomPractice")
    @ResponseBody
    public VResult<?> queryTownRoomPractice(HttpServletRequest request, Long townRoomId) {
        VResult<JrPractice> vResult = new VResult<>(false);
        try {
            List<JrPractice> jrPractices = jrPracticeRepo.findByTownRoomId(townRoomId);
            vResult.setContents(jrPractices);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryCountryPractice")
    @ResponseBody
    public VResult<?> queryCountryPractice(HttpServletRequest request, Long countryId) {
        VResult<JrPractice> vResult = new VResult<>(false);
        try {
            List<JrPractice> jrPractices = jrPracticeRepo.findByCountryId(countryId);
            vResult.setContents(jrPractices);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/addPractice")
    @ResponseBody
    public VResult<?> addPractice(@RequestBody JrPractice jrPractice, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrPractice.setStatus(1);
            jrPractice.setCreateUserId(userId);
            jrPracticeRepo.save(jrPractice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/delPractice")
    @ResponseBody
    public VResult<?> delPractice(Long practiceId, HttpServletRequest request) {
        VResult<JrCenter> vResult = new VResult<>(false);
        try {
            JrPractice jrPractice = jrPracticeRepo.findById(practiceId);
            jrPracticeRepo.delete(jrPractice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryPracticeInfo")
    @ResponseBody
    public VResult<?> queryPracticeInfo(HttpServletRequest request, Long practiceId) {
        VResult<JrPractice> vResult = new VResult<>(false);
        try {
            JrPractice jrPractice = jrPracticeRepo.findById(practiceId);
            vResult.setContent(jrPractice);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/practiceContenthave")
    @ResponseBody
    public VResult<?> practiceContenthave(HttpServletRequest request, Long id) {
        VResult<Boolean> vResult = new VResult<>(false);
        try {
            JrPractice jrPractice = jrPracticeRepo.findById(id);
            if(StringUtils.isBlank(jrPractice.getContent())){
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

    @PostMapping("/addPracticeContent")
    @ResponseBody
    public VResult<?> addPracticeContent(@RequestBody JrPractice jrPractice, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrPractice jrPracticeOri = jrPracticeRepo.findById(jrPractice.getId());
            jrPracticeOri.setContent(jrPractice.getContent());
            jrPracticeOri.setPic(jrPractice.getPic());
            jrPracticeOri.setUpdateUserId(userId);
            jrPracticeRepo.save(jrPracticeOri);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }


    @PostMapping("/updatePractice")
    @ResponseBody
    public VResult<?> updatePractice(@RequestBody JrPractice jrPractice, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrPractice jrPracticeOri = jrPracticeRepo.findById(jrPractice.getId());
            jrPracticeOri.setName(jrPractice.getName());
            jrPracticeOri.setContent(jrPractice.getContent());
            jrPracticeOri.setPic(jrPractice.getPic());
            jrPracticeOri.setUpdateUserId(userId);
            jrPracticeRepo.save(jrPracticeOri);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }
}
