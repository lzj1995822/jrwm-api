package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
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
import java.util.stream.Collectors;

@Controller
public class JrCountryController {

    private static final Logger logger = LoggerFactory.getLogger(JrCountryController.class);

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

    @PostMapping("/addCountry")
    @ResponseBody
    public VResult<?> addCountry(@RequestBody JrCountry jrCountry, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrCountry.setCreateUserId(userId);
            jrCountryRepo.save(jrCountry);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryCountryByTownId")
    @ResponseBody
    public VResult<?> queryCountryByTownId(HttpServletRequest request, Long townId) {
        VResult<JrCountry> vResult = new VResult<>(false);
        try {
            List<JrCountry> jrCountryList = jrCountryRepo.findByTownId(townId);
            vResult.setContents(jrCountryList);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/delCountry")
    @ResponseBody
    public VResult<?> delCountry(Long countryId, HttpServletRequest request) {
        VResult<JrTown> vResult = new VResult<>(false);
        try {
            JrCountry jrCountry = jrCountryRepo.findById(countryId);
            jrCountryRepo.delete(jrCountry);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/updateCountryName")
    @ResponseBody
    public VResult<?> updateCountryName(Long id, String name, HttpServletRequest request) {
        VResult<JrTown> vResult = new VResult<>(false);
        try {
            JrCountry jrCountry = jrCountryRepo.findById(id);
            jrCountry.setName(name);
            jrCountryRepo.save(jrCountry);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //年度计划查询
    @PostMapping("/queryPlansForCountry")
    @ResponseBody
    public VResult<?> queryPlansForCountry(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<Page> vResult = new VResult<>(false);
        try {
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            List<JrPlanExecuteResult> jrPlanExecuteResultList = jrPlanExecuteResultRepo.findByCountryId(jrUser.getCountryId());
            Page<JrPlan> page = jrPlanRepo.findByIdIn(jrPlanExecuteResultList.stream().map(t -> t.getPlanId()).collect(Collectors.toList()), pageAble);
            vResult.setContent(page);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }
}
