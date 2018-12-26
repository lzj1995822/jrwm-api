package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
import com.zkf.mysql.repository.*;
import com.zkf.page.Paginator;
import com.zkf.pojo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JrfeatureController {

    private static final Logger logger = LoggerFactory.getLogger(JrfeatureController.class);

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

    @PostMapping("/queryFeature")
    @ResponseBody
    public VResult<?> queryFeature(HttpServletRequest request) {
        VResult<JrPlanExecuteResult> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            List<JrPlanExecuteResult> jrPlanExecuteResults = jrPlanExecuteResultRepo.findByFeature(1);
            vResult.setContents(jrPlanExecuteResults);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryFeatureForFront")
    @ResponseBody
    public VResult<?> queryFeatureForFront(HttpServletRequest request, Integer pageNum, Integer pageSize, Long centerId,
                                           Long townId, Long countryId, Long practiceId) {
        VResult<PageResult> vResult = new VResult<>(false);
        try {
            PageResult pageResult = new PageResult();
            Paginator paginator = new Paginator();
            List<Long> countryIdS = new ArrayList<>();
            if (countryId != null) {
                countryIdS.add(countryId);
            } else {
                if (townId != null) {
                    List<JrCountry> jrCountryList = jrCountryRepo.findByTownId(townId);
                    countryIdS = jrCountryList.stream().map(j -> j.getId()).collect(Collectors.toList());
                }
            }
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            Specification<JrPlanExecuteResult> specification = getWhereClause(centerId, countryIdS, practiceId);
            Page<JrPlanExecuteResult> result = jrPlanExecuteResultRepo.findAll(specification, new PageRequest(pageNum - 1, pageSize, sort));
            List<QueryFeatureForFrontDTO> queryFeatureForFrontDTOS = new ArrayList<>();
            for(JrPlanExecuteResult jrPlanExecuteResult : result.getContent()){
                QueryFeatureForFrontDTO queryFeatureForFrontDTO = new QueryFeatureForFrontDTO();
                JrCenter jrCenter = jrCenterRepo.findById(jrPlanExecuteResult.getCheckCenterId());
                JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
                JrCountry jrCountry = jrCountryRepo.findById(jrPlanExecuteResult.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                queryFeatureForFrontDTO.setCenterName(jrCenter.getName());
                queryFeatureForFrontDTO.setContent(jrPlanExecuteResult.getResultContent());
                String[] str = jrPlanExecuteResult.getResultPic().split("\\|");
                queryFeatureForFrontDTO.setPic(Arrays.asList(str).stream().map(s -> "http://47.254.44.188:8088/files/"+s).collect(Collectors.toList()));
                queryFeatureForFrontDTO.setResultId(jrPlanExecuteResult.getId());
                queryFeatureForFrontDTO.setPlanName(jrPlan.getName());
                queryFeatureForFrontDTO.setCompleteTime(jrPlanExecuteResult.getCompleteTime());
                queryFeatureForFrontDTO.setTownName(jrTown.getName());
                queryFeatureForFrontDTO.setCountryName(jrCountry.getName());
                queryFeatureForFrontDTOS.add(queryFeatureForFrontDTO);
            }
            paginator.setTotalCount((int) result.getTotalElements());
            paginator.setTotalPage(result.getTotalPages());
            pageResult.setList(queryFeatureForFrontDTOS);
            pageResult.setPaginator(paginator);
            vResult.setContent(pageResult);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    private Specification<JrPlanExecuteResult> getWhereClause(Long centerId, List<Long> countryIdS, Long practiceId) {
        return new Specification<JrPlanExecuteResult>() {
            @Override
            public Predicate toPredicate(Root<JrPlanExecuteResult> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(countryIdS)) {
                    CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("countryId"));
                    for (Long l : countryIdS) {
                        in.value(l);
                    }
                    predicate.add(in);
                }
                if (centerId != null) {
                    predicate.add(criteriaBuilder.equal(root.get("checkCenterId").as(Long.class), centerId));
                }
                if (practiceId != null) {
                    predicate.add(criteriaBuilder.equal(root.get("practiceId").as(Long.class), practiceId));
                } else {
                    predicate.add(criteriaBuilder.equal(root.get("feature").as(Integer.class), 1));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


    @PostMapping("/querySingleFeature")
    @ResponseBody
    public VResult<?> querySingleFeature(HttpServletRequest request, Long resultId) {
        VResult<QuerySingleFeatureDTO> vResult = new VResult<>(false);
        try {
            QuerySingleFeatureDTO querySingleFeatureDTO = new QuerySingleFeatureDTO();
            JrPlanExecuteResult jrPlanExecuteResult = jrPlanExecuteResultRepo.findById(resultId);
            JrCenter jrCenter = jrCenterRepo.findById(jrPlanExecuteResult.getCheckCenterId());
            JrPractice jrPractice = jrPracticeRepo.findById(jrPlanExecuteResult.getPracticeId());
            JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
            querySingleFeatureDTO.setCenterName(jrCenter.getName());
            querySingleFeatureDTO.setAccessory("http://47.254.44.188:8088/files/"+jrPlan.getAccessory());
            querySingleFeatureDTO.setExpireTime(jrPlan.getExpireTime());
            querySingleFeatureDTO.setPlanContent(jrPlan.getContent());
            querySingleFeatureDTO.setPracticeName(jrPractice.getName());
            querySingleFeatureDTO.setResultContent(jrPlanExecuteResult.getResultContent());
            querySingleFeatureDTO.setResultPic(Arrays.asList(jrPlanExecuteResult.getResultPic().split("\\|")).stream().map(s -> "http://47.254.44.188:8088/files/"+s).collect(Collectors.toList()));
            querySingleFeatureDTO.setResultVideo(jrPlanExecuteResult.getResultVideo());
            querySingleFeatureDTO.setPlanName(jrPlan.getName());
            vResult.setContent(querySingleFeatureDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }
}
