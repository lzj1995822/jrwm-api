package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
import com.zkf.mysql.repository.*;
import com.zkf.page.Paginator;
import com.zkf.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JrSelfPlanController {

    private static final Logger logger = LoggerFactory.getLogger(JrSelfPlanController.class);

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

    //自选活动
    @PostMapping("/publishSelfPlan")
    @ResponseBody
    public VResult<?> publishSelfPlan(@RequestBody PublishPlanDTO publishPlanDTO, HttpServletRequest request) {
        VResult<EduUser> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            JrCountry jrCountry = jrCountryRepo.findById(jrUser.getCountryId());
            JrTownRoom jrTownRoom = jrTownRoomRepo.findByCenterIdAndTownId(publishPlanDTO.getCenterId(), jrCountry.getTownId());
            JrPlan jrPlan = new JrPlan();
            jrPlan.setCheckStatus(1);
            jrPlan.setCreateUserId(userId);
            jrPlan.setCenterId(publishPlanDTO.getCenterId());
            jrPlan.setCountryId(jrUser.getCountryId());
            jrPlan.setName(publishPlanDTO.getName());
            jrPlan.setContent(publishPlanDTO.getContent());
            jrPlan.setSelectType(2);
            jrPlan.setAccessory(publishPlanDTO.getAccessory());
            jrPlan.setExpireTime(new Timestamp(sdf.parse(publishPlanDTO.getExpireTime()).getTime()));
            jrPlan.setTownRoomId(jrTownRoom.getId());
            jrPlan = jrPlanRepo.save(jrPlan);
            //村领取计划
//            JrPlanExecuteResult jrPlanExecuteResult = new JrPlanExecuteResult();
//            jrPlanExecuteResult.setPlanId(jrPlan.getId());
//            jrPlanExecuteResult.setCountryId(jrUser.getCountryId());
//            jrPlanExecuteResult.setExecuteStatus(0);
//            jrPlanExecuteResult.setCheckStatus(1);
//            jrPlanExecuteResult.setCheckTownRoomId(jrTownRoom.getId());
//            jrPlanExecuteResult.setCheckCenterId(publishPlanDTO.getCenterId());
//            jrPlanExecuteResult.setCreateUserId(userId);
//            jrPlanExecuteResult.setResultType(1);
//            jrPlanExecuteResultRepo.save(jrPlanExecuteResult);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //自选活动
    @RequestMapping("/querySelfPlanList")
    @ResponseBody
    public VResult<?> querySelfPlanList(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<PageResult> vResult = new VResult<>(false);
        try {
            PageResult pageResult = new PageResult();
            Paginator paginator = new Paginator();
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            Page<JrPlan> jrPlanPage = jrPlanRepo.findByCountryId(jrUser.getCountryId(), pageAble);
            List<QuerySelfPlanListDTO> querySelfPlanListDTOS = new ArrayList<>();
            for(JrPlan jrPlan : jrPlanPage.getContent()){
                QuerySelfPlanListDTO querySelfPlanListDTO = new QuerySelfPlanListDTO();
                JrCenter jrCenter = jrCenterRepo.findById(jrPlan.getCenterId());
                querySelfPlanListDTO.setCenterName(jrCenter.getName());
                querySelfPlanListDTO.setExpireTime(jrPlan.getExpireTime());
                querySelfPlanListDTO.setCheckStatus(jrPlan.getCheckStatus());
                querySelfPlanListDTO.setId(jrPlan.getId());
                querySelfPlanListDTO.setName(jrPlan.getName());
                querySelfPlanListDTOS.add(querySelfPlanListDTO);
            }
            paginator.setTotalCount((int) jrPlanPage.getTotalElements());
            paginator.setTotalPage(jrPlanPage.getTotalPages());
            pageResult.setPaginator(paginator);
            pageResult.setList(querySelfPlanListDTOS);
            vResult.setContent(pageResult);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //功能室审核列表
    @PostMapping("/querySelfPlanCheckListForTownRoom")
    @ResponseBody
    public VResult<?> querySelfPlanCheckListForTownRoom(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<CheckForTownRoomResponseDTO> vResult = new VResult<>(false);
        try {
            CheckForTownRoomResponseDTO result = new CheckForTownRoomResponseDTO();
            Paginator paginator = new Paginator();
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            Page<JrPlan> jrPlanPage = jrPlanRepo.findByTownRoomIdAndCheckStatus(jrUser.getTownRoomId(), 1, pageAble);
            List<JrPlan> jrPlanList = jrPlanPage.getContent();
            List<CheckForTownRoomDTO> checkForTownRoomDTOS = new ArrayList<>();
            for(JrPlan jrPlan : jrPlanList){
                CheckForTownRoomDTO checkForTownRoomDTO = new CheckForTownRoomDTO();
                JrCountry jrCountry = jrCountryRepo.findById(jrPlan.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                checkForTownRoomDTO.setTownName(jrTown.getName());
                checkForTownRoomDTO.setResultId(jrPlan.getId());
                checkForTownRoomDTO.setCountryName(jrCountry.getName());
                checkForTownRoomDTO.setCompleteTime(jrPlan.getExpireTime());
                checkForTownRoomDTO.setPlanName(jrPlan.getName());
                checkForTownRoomDTOS.add(checkForTownRoomDTO);
            }
            paginator.setTotalCount((int) jrPlanPage.getTotalElements());
            paginator.setTotalPage(jrPlanPage.getTotalPages());
            result.setCheckForTownRoomDTOS(checkForTownRoomDTOS);
            result.setPaginator(paginator);
            vResult.setContent(result);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //功能室审核列表
    @PostMapping("/checkSelfPlanForTownRoom")
    @ResponseBody
    public VResult<?> checkSelfPlanForTownRoom(HttpServletRequest request, Long planId, Integer status, Integer integral) {
        VResult<CheckForTownRoomResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            JrPlan jrPlan = jrPlanRepo.findById(planId);
            jrPlan.setUpdateUserId(jrUser.getId());
            jrPlan.setCheckStatus(status);
            jrPlan.setIntegral(integral);
            jrPlanRepo.save(jrPlan);
            if(status == 3){
                //村领取计划
                JrPlanExecuteResult jrPlanExecuteResult = new JrPlanExecuteResult();
                jrPlanExecuteResult.setPlanId(jrPlan.getId());
                jrPlanExecuteResult.setCountryId(jrPlan.getCountryId());
                jrPlanExecuteResult.setExecuteStatus(0);
                jrPlanExecuteResult.setCheckStatus(1);
                jrPlanExecuteResult.setCheckTownRoomId(jrPlan.getTownRoomId());
                jrPlanExecuteResult.setCheckCenterId(jrPlan.getCenterId());
                jrPlanExecuteResult.setCreateUserId(userId);
                jrPlanExecuteResult.setResultType(1);
                jrPlanExecuteResultRepo.save(jrPlanExecuteResult);
            }
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //分中心审核列表
    @PostMapping("/querySelfPlanCheckListForCenter")
    @ResponseBody
    public VResult<?> querySelfPlanCheckListForCenter(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<CheckForTownRoomResponseDTO> vResult = new VResult<>(false);
        try {
            CheckForTownRoomResponseDTO result = new CheckForTownRoomResponseDTO();
            Paginator paginator = new Paginator();
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            Page<JrPlan> jrPlanPage = jrPlanRepo.findByCenterIdAndCheckStatus(jrUser.getCenterId(), 2, pageAble);
            List<JrPlan> jrPlanList = jrPlanPage.getContent();
            List<CheckForTownRoomDTO> checkForTownRoomDTOS = new ArrayList<>();
            for(JrPlan jrPlan : jrPlanList){
                CheckForTownRoomDTO checkForTownRoomDTO = new CheckForTownRoomDTO();
                JrCountry jrCountry = jrCountryRepo.findById(jrPlan.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                checkForTownRoomDTO.setTownName(jrTown.getName());
                checkForTownRoomDTO.setResultId(jrPlan.getId());
                checkForTownRoomDTO.setCountryName(jrCountry.getName());
                checkForTownRoomDTO.setCompleteTime(jrPlan.getExpireTime());
                checkForTownRoomDTO.setPlanName(jrPlan.getName());
                checkForTownRoomDTOS.add(checkForTownRoomDTO);
            }
            paginator.setTotalCount((int) jrPlanPage.getTotalElements());
            paginator.setTotalPage(jrPlanPage.getTotalPages());
            result.setCheckForTownRoomDTOS(checkForTownRoomDTOS);
            result.setPaginator(paginator);
            vResult.setContent(result);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //后台查看自选活动
    @PostMapping("/querySelfPlanListForPlatform")
    @ResponseBody
    public VResult<?> querySelfPlanListForPlatform(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<PageResult> vResult = new VResult<>(false);
        try {
            PageResult pageResult = new PageResult();
            Paginator paginator = new Paginator();
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            Page<JrPlan> jrPlanPage = jrPlanRepo.findBySelectType(2, pageAble);
            List<CheckForTownRoomDTO> checkForTownRoomDTOS = new ArrayList<>();
            for(JrPlan jrPlan : jrPlanPage.getContent()){
                JrCountry jrCountry = jrCountryRepo.findById(jrPlan.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                CheckForTownRoomDTO checkForTownRoomDTO = new CheckForTownRoomDTO();
                checkForTownRoomDTO.setPlanName(jrPlan.getName());
                checkForTownRoomDTO.setCompleteTime(jrPlan.getExpireTime());
                checkForTownRoomDTO.setCountryName(jrCountry.getName());
                checkForTownRoomDTO.setTownName(jrTown.getName());
                checkForTownRoomDTO.setResultId(jrPlan.getId());
                checkForTownRoomDTO.setCheckStatus(jrPlan.getCheckStatus());
                checkForTownRoomDTOS.add(checkForTownRoomDTO);
            }
            paginator.setTotalCount((int) jrPlanPage.getTotalElements());
            paginator.setTotalPage(jrPlanPage.getTotalPages());
            pageResult.setPaginator(paginator);
            pageResult.setList(checkForTownRoomDTOS);
            vResult.setContent(pageResult);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }
}
