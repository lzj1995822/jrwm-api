package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
import com.zkf.mysql.repository.*;
import com.zkf.pojo.JrOrganizationResponseDTO;
import com.zkf.pojo.JrOrganizationResquestDTO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JrCenterController {

    private static final Logger logger = LoggerFactory.getLogger(JrCenterController.class);

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

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/addCenter")
    @ResponseBody
    public VResult<?> addCenter(@RequestBody JrCenter jrCenter, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            String name = jrCenter.getName();

            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrCenter.setName(jrCenter.getName()+"分中心");
            jrCenter.setCreateUserId(userId);
            jrCenter = jrCenterRepo.save(jrCenter);
            //添加镇室
            List<JrTown> jrTowns = jrTownRepo.findAll();
            for(JrTown jrTown : jrTowns){
                JrTownRoom jrTownRoom = new JrTownRoom();
                jrTownRoom.setCreateUserId(userId);
                jrTownRoom.setName(name+"室");
                jrTownRoom.setTownId(jrTown.getId());
                jrTownRoom.setCenterId(jrCenter.getId());
                jrTownRoomRepo.save(jrTownRoom);
            }
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryCenter")
    @ResponseBody
    public VResult<?> queryCenter(HttpServletRequest request) {
        VResult<JrCenter> vResult = new VResult<>(false);
        try {
            List<JrCenter> jrCenters = jrCenterRepo.findAll();
            vResult.setContents(jrCenters);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/delCenter")
    @ResponseBody
    public VResult<?> delCenter(Long centerId, HttpServletRequest request) {
        VResult<JrCenter> vResult = new VResult<>(false);
        try {
            JrCenter jrCenter = jrCenterRepo.findById(centerId);
            jrCenterRepo.delete(jrCenter);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/updateCenterName")
    @ResponseBody
    public VResult<?> updateCenterName(Long id, String name, HttpServletRequest request) {
        VResult<JrCenter> vResult = new VResult<>(false);
        try {
            JrCenter jrCenter = jrCenterRepo.findById(id);
            jrCenter.setName(name);
            jrCenterRepo.save(jrCenter);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //年度计划查询
    @PostMapping("/queryPlansForCenter")
    @ResponseBody
    public VResult<?> queryPlansForCenter(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<Page> vResult = new VResult<>(false);
        try {
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            List<JrPlanExecuteResult> jrPlanExecuteResultList = jrPlanExecuteResultRepo.findByCheckCenterId(jrUser.getCenterId());
            Page<JrPlan> page = jrPlanRepo.findByIdIn(jrPlanExecuteResultList.stream().map(t -> t.getPlanId()).collect(Collectors.toList()), pageAble);
            vResult.setContent(page);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

}
