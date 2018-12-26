package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
import com.zkf.mysql.repository.*;
import com.zkf.pojo.JrOrganizationResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JrTownController {

    private static final Logger logger = LoggerFactory.getLogger(JrTownController.class);

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

    @PostMapping("/addTown")
    @ResponseBody
    public VResult<?> addTown(@RequestBody JrTown jrTown, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrTown.setCreateUserId(userId);
            jrTown.setIntegral(0);
            jrTownRepo.save(jrTown);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryTown")
    @ResponseBody
    public VResult<?> queryTown(HttpServletRequest request) {
        VResult<JrTown> vResult = new VResult<>(false);
        try {
            List<JrTown> jrTowns = jrTownRepo.findAll();
            vResult.setContents(jrTowns);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/delTown")
    @ResponseBody
    public VResult<?> delTown(Long townId, HttpServletRequest request) {
        VResult<JrTown> vResult = new VResult<>(false);
        try {
            JrTown jrTown = jrTownRepo.findById(townId);
            jrTownRepo.delete(jrTown);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/querySelfTown")
    @ResponseBody
    public VResult<?> querySelfTown(HttpServletRequest request) {
        List<JrTown> list = new ArrayList<>();
        try {
            list = jrTownRepo.findAll();
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return VResult.of(true, list);
    }
}
