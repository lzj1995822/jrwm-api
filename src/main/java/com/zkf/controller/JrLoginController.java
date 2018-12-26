package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.common.utils.MD5;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.EduUser;
import com.zkf.mysql.entity.JrUser;
import com.zkf.mysql.repository.JrUserRepo;
import com.zkf.pojo.JrRegisterDTO;
import com.zkf.pojo.UserLoginReaponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class JrLoginController {

    private static final Logger logger = LoggerFactory.getLogger(JrLoginController.class);

    @Resource
    private JrUserRepo jrUserRepo;

    /**
     * 注册
     *
     * @param jrRegisterDTO
     * @return
     */
    @PostMapping("/userRegister")
    @ResponseBody
    public VResult<?> userRegister(@RequestBody JrRegisterDTO jrRegisterDTO) {
        VResult<EduUser> vResult = new VResult<>(false);
        try {
            JrUser jrUser = jrUserRepo.findByUserId(jrRegisterDTO.getUserId());
            if(jrUser != null){
                vResult.setMessage("该账号已被注册");
                return vResult;
            }
            JrUser jrUserSave = new JrUser();
            jrUserSave.setUserId(jrRegisterDTO.getUserId());
            jrUserSave.setUserPwd(MD5.computeToHexStr(jrRegisterDTO.getUserPwd()));
            jrUserSave.setType(jrRegisterDTO.getType());
            jrUserRepo.save(jrUserSave);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("注册失败", e);
        }
        return vResult;
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/userLogin")
    @ResponseBody
    public VResult<?> userLogin(String userId, String userPwd) {
        VResult<UserLoginReaponseDTO> vResult = new VResult<>(false);
        try {
            JrUser jrUser = jrUserRepo.findByUserIdAndUserPwd(userId, MD5.computeToHexStr(userPwd));
            if(jrUser == null){
                vResult.setMessage("用户名密码错误");
                return vResult;
            }
            String token = JwtUtils.createToken(jrUser.getId());
            UserLoginReaponseDTO userLoginReaponseDTO = new UserLoginReaponseDTO();
            userLoginReaponseDTO.setUserType(jrUser.getType());
            userLoginReaponseDTO.setToken(token);
            userLoginReaponseDTO.setUserName(jrUser.getUserName());
            userLoginReaponseDTO.setLastLogin(new Date());
            vResult.setContent(userLoginReaponseDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("登录失败", e);
        }
        return vResult;
    }

}
