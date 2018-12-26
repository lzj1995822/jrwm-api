package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
import com.zkf.mysql.repository.*;
import com.zkf.page.Paginator;
import com.zkf.pojo.AfterResultDTO;
import com.zkf.pojo.AfterResultResponseDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JrTownRoomController {

    private static final Logger logger = LoggerFactory.getLogger(JrTownRoomController.class);

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

    @PostMapping("/queryTownRoom")
    @ResponseBody
    public VResult<?> queryTown(Long id, HttpServletRequest request) {
        VResult<JrTownRoom> vResult = new VResult<>(false);
        try {
            List<JrTownRoom> jrTownRoomList = jrTownRoomRepo.findByTownId(id);
            vResult.setContents(jrTownRoomList);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/addTownRoom")
    @ResponseBody
    public VResult<?> addTownRoom(@RequestBody JrTownRoom jrTownRoom, HttpServletRequest request) {
        VResult<JrTownRoom> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrTownRoom.setCreateUserId(userId);
            jrTownRoomRepo.save(jrTownRoom);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/delTownRoom")
    @ResponseBody
    public VResult<?> delTownRoom(Long id, HttpServletRequest request) {
        VResult<JrTownRoom> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrTownRoom jrTownRoom = jrTownRoomRepo.findById(id);
            jrTownRoomRepo.delete(jrTownRoom);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/updateTownRoomName")
    @ResponseBody
    public VResult<?> updateTownRoomName(Long id, String name, HttpServletRequest request) {
        VResult<JrTownRoom> vResult = new VResult<>(false);
        try {
            JrTownRoom jrTownRoom = jrTownRoomRepo.findById(id);
            jrTownRoom.setName(name);
            jrTownRoomRepo.save(jrTownRoom);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //年度计划查询
    @PostMapping("/queryPlansForTownRoom")
    @ResponseBody
    public VResult<?> queryPlansForTownRoom(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<Page> vResult = new VResult<>(false);
        try {
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            List<JrPlanExecuteResult> jrPlanExecuteResultList = jrPlanExecuteResultRepo.findByCheckTownRoomId(jrUser.getTownRoomId());
            Page<JrPlan> page = jrPlanRepo.findByIdIn(jrPlanExecuteResultList.stream().map(t -> t.getPlanId()).collect(Collectors.toList()), pageAble);
            vResult.setContent(page);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //跟踪计划
    @PostMapping("/afterResult")
    @ResponseBody
    public VResult<?> afterResult(HttpServletRequest request, Integer pageNum, Integer pageSize, Long planId) {
        VResult<AfterResultResponseDTO> vResult = new VResult<>(false);
        try {
            AfterResultResponseDTO result = new AfterResultResponseDTO();
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            Page<JrPlanExecuteResult> jrPlanExecuteResultList = jrPlanExecuteResultRepo.findByCheckTownRoomIdAndPlanId(jrUser.getTownRoomId(), planId, pageAble);
            List<AfterResultDTO> afterResultDTOS = new ArrayList<>();
            for(JrPlanExecuteResult jrPlanExecuteResult : jrPlanExecuteResultList.getContent()){
                AfterResultDTO afterResultDTO = new AfterResultDTO();
                JrCountry jrCountry = jrCountryRepo.findById(jrPlanExecuteResult.getCountryId());
                afterResultDTO.setCountryName(jrCountry.getName());
                afterResultDTO.setResultId(jrPlanExecuteResult.getId());
                if(jrPlanExecuteResult.getExecuteStatus() == 0){
                    afterResultDTO.setStatus("未完成");
                } else {
                    if(jrPlanExecuteResult.getCheckStatus() == 4){
                        afterResultDTO.setStatus("已驳回");
                    } else if(jrPlanExecuteResult.getCheckStatus() == 1 || jrPlanExecuteResult.getCheckStatus() == 2){
                        afterResultDTO.setStatus("待审核");
                    } else if(jrPlanExecuteResult.getCheckStatus() == 3){
                        afterResultDTO.setStatus("已完成");
                    }
                }
                afterResultDTOS.add(afterResultDTO);
            }
            Paginator paginator = new Paginator();
            paginator.setTotalCount((int) jrPlanExecuteResultList.getTotalElements());
            paginator.setTotalPage(jrPlanExecuteResultList.getTotalPages());
            result.setPaginator(paginator);
            result.setAfterResultDTOS(afterResultDTOS);
            vResult.setContent(result);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

}
