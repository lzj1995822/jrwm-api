package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
import com.zkf.mysql.repository.*;
import com.zkf.page.Paginator;
import com.zkf.pojo.PageResult;
import com.zkf.pojo.QueryFeatureForFrontDTO;
import com.zkf.pojo.QuerySingleFeatureDTO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
public class JrIssueController {

    private static final Logger logger = LoggerFactory.getLogger(JrIssueController.class);

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

    @Resource
    private JrIssueRepo jrIssueRepo;

    @PostMapping("/addIssue")
    @ResponseBody
    public VResult<?> addIssue(HttpServletRequest request, JrIssue jrIssue) {
        VResult<JrPlanExecuteResult> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrIssue.setCreateUserId(userId);
            jrIssueRepo.save(jrIssue);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

}
