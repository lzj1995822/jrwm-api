package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.common.utils.MD5;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JrPlanController {

    private static final Logger logger = LoggerFactory.getLogger(JrPlanController.class);

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

    //分中心发布计划
    @PostMapping("/publishPlan")
    @ResponseBody
    public VResult<?> publishPlan(@RequestBody PublishPlanDTO publishPlanDTO, HttpServletRequest request) {
        VResult<EduUser> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            List<JrTownRoom> jrTownRooms = jrTownRoomRepo.findByCenterId(jrUser.getCenterId());
            JrPlan jrPlan = new JrPlan();
            jrPlan.setCheckStatus(1);
            jrPlan.setCreateUserId(userId);
            jrPlan.setCenterId(jrUser.getCenterId());
            jrPlan.setName(publishPlanDTO.getName());
            jrPlan.setContent(publishPlanDTO.getContent());
            jrPlan.setSelectType(1);
            jrPlan.setAccessory(publishPlanDTO.getAccessory());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            jrPlan.setExpireTime(new Timestamp(sdf.parse(publishPlanDTO.getExpireTime()).getTime()));
            jrPlan.setIntegral(publishPlanDTO.getIntegral());
            jrPlan = jrPlanRepo.save(jrPlan);
            //村领取计划
            List<JrCountry> jrCountryList = jrCountryRepo.findAll();
            for (JrCountry jrCountry : jrCountryList) {
                JrTownRoom jrTownRoom = null;
                for(JrTownRoom jrTownRoom1 : jrTownRooms){
                    if(jrTownRoom1.getTownId().equals(jrCountry.getTownId())){
                        jrTownRoom = jrTownRoom1;
                        break;
                    }
                }
                JrPlanExecuteResult jrPlanExecuteResult = new JrPlanExecuteResult();
                jrPlanExecuteResult.setPlanId(jrPlan.getId());
                jrPlanExecuteResult.setCountryId(jrCountry.getId());
                jrPlanExecuteResult.setExecuteStatus(0);
                jrPlanExecuteResult.setCheckStatus(1);
                jrPlanExecuteResult.setCheckTownRoomId(jrTownRoom == null ? null : jrTownRoom.getId());
                jrPlanExecuteResult.setCheckCenterId(jrUser.getCenterId());
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

    //查看分中心发布的计划
    @PostMapping("/queryPlan")
    @ResponseBody
    public VResult<?> queryPlan(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<Page> vResult = new VResult<>(false);
        try {
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            Page<JrPlan> jrPlans = jrPlanRepo.findByCenterId(jrUser.getCenterId(), pageAble);
            vResult.setContent(jrPlans);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/querySinglePlan")
    @ResponseBody
    public VResult<?> querySinglePlan(HttpServletRequest request, Long planId) {
        VResult<QuerySinglePlanResDTO> vResult = new VResult<>(false);
        try {
            QuerySinglePlanResDTO result = new QuerySinglePlanResDTO();
            JrPlan jrPlan = jrPlanRepo.findById(planId);
            JrCenter jrCenter = jrCenterRepo.findById(jrPlan.getCenterId());
            result.setExpireTime(jrPlan.getExpireTime());
            result.setCenterName(jrCenter.getName());
            result.setAccessory("http://47.254.44.188:8088/files/"+jrPlan.getAccessory());
            result.setContent(jrPlan.getContent());
            result.setName(jrPlan.getName());
            result.setIntegral(jrPlan.getIntegral());
            vResult.setContent(result);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }


    //删除计划
    @PostMapping("/delPlan")
    @ResponseBody
    public VResult<?> delPlan(Long planId, HttpServletRequest request) {
        VResult<EduUser> vResult = new VResult<>(false);
        try {
            JrPlan jrPlan = jrPlanRepo.findById(planId);
            jrPlanRepo.delete(jrPlan);
            List<JrPlanExecuteResult> jrPlanExecuteResults = jrPlanExecuteResultRepo.findByPlanId(planId);
            for(JrPlanExecuteResult jrPlanExecuteResult : jrPlanExecuteResults){
                jrPlanExecuteResultRepo.delete(jrPlanExecuteResult);
            }
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //查看我要执行的计划(固选)
    @PostMapping("/myExecutePlan")
    @ResponseBody
    public VResult<?> myExecutePlan(HttpServletRequest request, Integer pageNum, Integer pageSize, Integer selectType) {
        VResult<MyExecutePlanResponseDTO> vResult = new VResult<>(false);
        try {
            MyExecutePlanResponseDTO myExecutePlanResponseDTO = new MyExecutePlanResponseDTO();
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            List<JrPlanExecuteResult> jrPlanExecuteResults = jrPlanExecuteResultRepo.findByCountryIdAndExecuteStatus(jrUser.getCountryId(), 0);
            List<Long> planIds = jrPlanExecuteResults.stream().map(j -> j.getPlanId()).collect(Collectors.toList());
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Page<JrPlan> page = jrPlanRepo.findBySelectTypeAndIdIn(selectType, planIds, pageAble);
            List<MyExecutePlanDTO> result = new ArrayList<>();
            for(JrPlan jrPlan : page.getContent()){
                JrCenter jrCenter = jrCenterRepo.findById(jrPlan.getCenterId());
                MyExecutePlanDTO myExecutePlanDTO = new MyExecutePlanDTO();
                myExecutePlanDTO.setPlanContent(jrPlan.getContent());
                myExecutePlanDTO.setPlanName(jrPlan.getName());
                myExecutePlanDTO.setPlanId(jrPlan.getId());
                myExecutePlanDTO.setCenterType(jrCenter.getName());
                myExecutePlanDTO.setExpireTime(jrPlan.getExpireTime());
                result.add(myExecutePlanDTO);
            }
            Paginator paginator = new Paginator();
            paginator.setTotalPage(page.getTotalPages());
            paginator.setTotalCount((int) page.getTotalElements());
            myExecutePlanResponseDTO.setMyExecutePlanDTOS(result);
            myExecutePlanResponseDTO.setPaginator(paginator);
            vResult.setContent(myExecutePlanResponseDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/querySinglePlanForFront")
    @ResponseBody
    public VResult<?> querySinglePlanForFront(HttpServletRequest request, Long planId) {
        VResult<QuerySinglePlanForFrontDTO> vResult = new VResult<>(false);
        try {
            QuerySinglePlanForFrontDTO result = new QuerySinglePlanForFrontDTO();
            JrPlan jrPlan = jrPlanRepo.findById(planId);
            JrCenter jrCenter = jrCenterRepo.findById(jrPlan.getCenterId());
            result.setExpireTime(jrPlan.getExpireTime());
            result.setCenterType(jrCenter.getName());
            result.setAccessory("http://47.254.44.188:8088/files/"+jrPlan.getAccessory());
            result.setPlanContent(jrPlan.getContent());
            result.setPlanName(jrPlan.getName());
            vResult.setContent(result);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //获取实践点
    @PostMapping("/queryPractice")
    @ResponseBody
    public VResult<?> queryPractice(HttpServletRequest request, Long planId) {
        VResult<JrPractice> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            JrCountry jrCountry = jrCountryRepo.findById(jrUser.getCountryId());
            List<JrPractice> jrPractices = jrPracticeRepo.findByCountryId(jrCountry.getId());
            JrPlan jrPlan = jrPlanRepo.findById(planId);
            List<JrPractice> jrPractices1 = jrPracticeRepo.findByCenterId(jrPlan.getCenterId());
            JrTownRoom jrTownRoom = jrTownRoomRepo.findByCenterIdAndTownId(jrPlan.getCenterId(), jrCountry.getTownId());
            List<JrPractice> jrPractices2 = jrPracticeRepo.findByTownRoomId(jrTownRoom.getId());
            jrPractices.addAll(jrPractices1);
            jrPractices.addAll(jrPractices2);
            vResult.setContents(jrPractices);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //执行计划
    @PostMapping("/executePlan")
    @ResponseBody
    public VResult<?> executePlan(@RequestBody JrPlanExecuteResultDTO jrPlanExecuteResultDTO, HttpServletRequest request) {
        VResult<EduUser> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            JrPlanExecuteResult jrPlanExecuteResult = jrPlanExecuteResultRepo.findByPlanIdAndCountryId(jrPlanExecuteResultDTO.getPlanId(), jrUser.getCountryId());
            jrPlanExecuteResult.setResultContent(jrPlanExecuteResultDTO.getResultContent());
            jrPlanExecuteResult.setResultPic(jrPlanExecuteResultDTO.getResultPic());
            jrPlanExecuteResult.setPracticeId(jrPlanExecuteResultDTO.getPracticeId());
            jrPlanExecuteResult.setResultVideo(jrPlanExecuteResultDTO.getResultVideo());
            jrPlanExecuteResult.setFeature(0);
            jrPlanExecuteResult.setExecuteStatus(1);
            jrPlanExecuteResult.setCompleteTime(new Timestamp(System.currentTimeMillis()));
            jrPlanExecuteResult.setResultType(1);
            jrPlanExecuteResultRepo.save(jrPlanExecuteResult);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //镇（室）审核列表
    @PostMapping("/checkPlanListForTownRoom")
    @ResponseBody
    public VResult<?> checkPlanListForTownRoom(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<CheckForTownRoomResponseDTO> vResult = new VResult<>(false);
        try {
            CheckForTownRoomResponseDTO checkForTownRoomResponseDTO = new CheckForTownRoomResponseDTO();
            Paginator paginator = new Paginator();
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            Page<JrPlanExecuteResult> page = jrPlanExecuteResultRepo.findByCheckStatusAndCheckTownRoomId(1, jrUser.getTownRoomId(), pageAble);
            List<JrPlanExecuteResult> jrPlanExecuteResults = page.getContent();
            List<CheckForTownRoomDTO> checkForTownRoomDTOS = new ArrayList<>();
            for(JrPlanExecuteResult jrPlanExecuteResult : jrPlanExecuteResults){
                CheckForTownRoomDTO checkForTownRoomDTO = new CheckForTownRoomDTO();
                JrCountry jrCountry = jrCountryRepo.findById(jrPlanExecuteResult.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
                checkForTownRoomDTO.setTownName(jrTown.getName());
                checkForTownRoomDTO.setResultId(jrPlanExecuteResult.getId());
                checkForTownRoomDTO.setCountryName(jrCountry.getName());
                checkForTownRoomDTO.setCompleteTime(jrPlanExecuteResult.getCompleteTime());
                checkForTownRoomDTO.setPlanName(jrPlan.getName());
                checkForTownRoomDTOS.add(checkForTownRoomDTO);
            }
            paginator.setTotalCount((int) page.getTotalElements());
            paginator.setTotalPage(page.getTotalPages());
            checkForTownRoomResponseDTO.setCheckForTownRoomDTOS(checkForTownRoomDTOS);
            checkForTownRoomResponseDTO.setPaginator(paginator);
            vResult.setContent(checkForTownRoomResponseDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //审核查看
    @PostMapping("/queryPlanForTownRoom")
    @ResponseBody
    public VResult<?> queryPlanForTownRoom(Long resultId, HttpServletRequest request) {
        VResult<QueryPlanForTownRoomDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            QueryPlanForTownRoomDTO result = new QueryPlanForTownRoomDTO();
            JrPlanExecuteResult jrPlanExecuteResult = jrPlanExecuteResultRepo.findById(resultId);
            JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
            JrCenter jrCenter = jrCenterRepo.findById(jrPlan.getCenterId());
            JrPractice jrPractice = jrPracticeRepo.findById(jrPlanExecuteResult.getPracticeId());
            result.setPlanName(jrPlan.getName());
            result.setCompleteTime(jrPlanExecuteResult.getCompleteTime());
            result.setCenterName(jrCenter.getName());
            result.setPlanAccessory(jrPlan.getAccessory());
            result.setPlanContent(jrPlan.getContent());
            result.setResultId(jrPlanExecuteResult.getId());
            result.setResultContent(jrPlanExecuteResult.getResultContent());
            result.setResultAccessory(jrPlanExecuteResult.getResultPic());
            result.setPracticeName(jrPractice.getName());
            vResult.setContent(result);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //审核
    @PostMapping("/checkPlanForTownRoom")
    @ResponseBody
    public VResult<?> checkPlanForTownRoom(Long jrPlanExecuteResultId, Integer checkStatus, String memo, HttpServletRequest request) {
        VResult<EduUser> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrPlanExecuteResult jrPlanExecuteResult = jrPlanExecuteResultRepo.findById(jrPlanExecuteResultId);
            jrPlanExecuteResult.setCheckStatus(checkStatus);
            jrPlanExecuteResult.setMemo(memo);
            jrPlanExecuteResult.setUpdateUserId(userId);
            jrPlanExecuteResultRepo.save(jrPlanExecuteResult);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //分中心审核列表
    @PostMapping("/checkPlanListForCenter")
    @ResponseBody
    public VResult<?> checkPlanListForCenter(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        VResult<CheckForTownRoomResponseDTO> vResult = new VResult<>(false);
        try {
            CheckForTownRoomResponseDTO checkForTownRoomResponseDTO = new CheckForTownRoomResponseDTO();
            Paginator paginator = new Paginator();
            Pageable pageAble = new PageRequest(pageNum - 1, pageSize);
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUser = jrUserRepo.findById(userId);
            Page<JrPlanExecuteResult> page = jrPlanExecuteResultRepo.findByCheckStatusAndCheckCenterId(2, jrUser.getCenterId(), pageAble);
            List<JrPlanExecuteResult> jrPlanExecuteResults = page.getContent();
            List<CheckForTownRoomDTO> checkForTownRoomDTOS = new ArrayList<>();
            for(JrPlanExecuteResult jrPlanExecuteResult : jrPlanExecuteResults){
                CheckForTownRoomDTO checkForTownRoomDTO = new CheckForTownRoomDTO();
                JrCountry jrCountry = jrCountryRepo.findById(jrPlanExecuteResult.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
                checkForTownRoomDTO.setTownName(jrTown.getName());
                checkForTownRoomDTO.setResultId(jrPlanExecuteResult.getId());
                checkForTownRoomDTO.setCountryName(jrCountry.getName());
                checkForTownRoomDTO.setCompleteTime(jrPlanExecuteResult.getCompleteTime());
                checkForTownRoomDTO.setPlanName(jrPlan.getName());
                checkForTownRoomDTOS.add(checkForTownRoomDTO);
            }
            paginator.setTotalCount((int) page.getTotalElements());
            paginator.setTotalPage(page.getTotalPages());
            checkForTownRoomResponseDTO.setCheckForTownRoomDTOS(checkForTownRoomDTOS);
            checkForTownRoomResponseDTO.setPaginator(paginator);
            vResult.setContent(checkForTownRoomResponseDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //分中心审核
    @PostMapping("/checkPlanForCenter")
    @ResponseBody
    public VResult<?> checkPlanForCenter(Long jrPlanExecuteResultId, Integer checkStatus, String memo, HttpServletRequest request) {
        VResult<EduUser> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrPlanExecuteResult jrPlanExecuteResult = jrPlanExecuteResultRepo.findById(jrPlanExecuteResultId);
            jrPlanExecuteResult.setCheckStatus(checkStatus);
            jrPlanExecuteResult.setUpdateUserId(userId);
            jrPlanExecuteResult.setMemo(memo);
            jrPlanExecuteResultRepo.save(jrPlanExecuteResult);
            //镇领取积分
            if(checkStatus == 3){
                JrCountry jrCountry = jrCountryRepo.findById(jrPlanExecuteResult.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
                Integer integral = (jrTown.getIntegral() == null ? 0 :jrTown.getIntegral()) + jrPlan.getIntegral();
                jrTown.setIntegral(integral);
                jrTownRepo.save(jrTown);
            }
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //标记特色活动
    @PostMapping("/featurePlan")
    @ResponseBody
    public VResult<?> featurePlan(Long jrPlanExecuteResultId, HttpServletRequest request) {
        VResult<EduUser> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrPlanExecuteResult jrPlanExecuteResult = jrPlanExecuteResultRepo.findById(jrPlanExecuteResultId);
            jrPlanExecuteResult.setFeature(1);
            jrPlanExecuteResultRepo.save(jrPlanExecuteResult);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

//    //查看哪些需要审核
//    @PostMapping("/needCheckForCenter")
//    @ResponseBody
//    public VResult<?> needCheckForCenter(HttpServletRequest request) {
//        VResult<JrPlanExecuteResult> vResult = new VResult<>(false);
//        try {
//            Long userId = JwtUtils.getUserId(request.getHeader("token"));
//            JrUser jrUser = jrUserRepo.findById(userId);
//            if (jrUser.getType() == 2) {
//                List<JrPlanExecuteResult> jrPlanExecuteResults = jrPlanExecuteResultRepo.findByCheckStatusAndCheckCenterId(2, jrUser.getCenterId());
//                vResult.setContents(jrPlanExecuteResults);
//            } else if (jrUser.getType() == 4) {
//                List<JrPlanExecuteResult> jrPlanExecuteResults = jrPlanExecuteResultRepo.findByCheckStatusAndCheckTownId(1, jrUser.getTownId());
//                vResult.setContents(jrPlanExecuteResults);
//            }
//            vResult.setSuccess(true);
//        } catch (Exception e) {
//            logger.error("失败", e);
//        }
//        return vResult;
//    }

}
