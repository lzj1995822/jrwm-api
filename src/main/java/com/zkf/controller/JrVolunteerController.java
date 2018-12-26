package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.JrTownRoom;
import com.zkf.mysql.entity.JrVolunteer;
import com.zkf.mysql.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class JrVolunteerController {

    private static final Logger logger = LoggerFactory.getLogger(JrVolunteerController.class);

    @Resource
    private JrVolunteerRepo jrVolunteerRepo;

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

    @PostMapping("/queryVolunteer")
    @ResponseBody
    public VResult<?> queryVolunteer(HttpServletRequest request) {
        VResult<JrVolunteer> vResult = new VResult<>(false);
        try {
            List<JrVolunteer> jrVolunteerList = jrVolunteerRepo.findAll();
            vResult.setContents(jrVolunteerList);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/addVolunteer")
    @ResponseBody
    public VResult<?> addVolunteer(@RequestBody JrVolunteer jrVolunteer, HttpServletRequest request) {
        VResult<JrVolunteer> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrVolunteer.setCreateUserId(userId);
            jrVolunteerRepo.save(jrVolunteer);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

}
