package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.common.utils.MD5;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.EduUser;
import com.zkf.mysql.entity.JrOrganization;
import com.zkf.mysql.entity.JrUser;
import com.zkf.mysql.repository.JrOrganizationRepo;
import com.zkf.mysql.repository.JrQueryUserDTO;
import com.zkf.mysql.repository.JrUserRepo;
import com.zkf.pojo.*;
import org.apache.commons.lang.StringUtils;
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
import java.util.Iterator;
import java.util.List;

@Controller
public class JrOrganizationController {

    private static final Logger logger = LoggerFactory.getLogger(JrOrganizationController.class);

    @Resource
    private JrOrganizationRepo jrOrganizationRepo;

    @Resource
    private JrUserRepo jrUserRepo;

//    @PostMapping("/addOrganization")
//    @ResponseBody
//    public VResult<?> addOrganization(@RequestBody JrOrganizationResquestDTO jrOrganizationResquestDTO, HttpServletRequest request) {
//        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
//        try {
//            Long userId = JwtUtils.getUserId(request.getHeader("token"));
//            JrOrganization jrOrganization = new JrOrganization();
//            jrOrganization.setParentId(jrOrganizationResquestDTO.getParentId());
//            jrOrganization.setName(jrOrganizationResquestDTO.getName());
//            jrOrganization.setGender(jrOrganizationResquestDTO.getGender());
//            jrOrganization.setDepartment(jrOrganizationResquestDTO.getDepartment());
//            jrOrganization.setLevel(jrOrganizationResquestDTO.getLevel());
//            jrOrganization.setPosition(jrOrganizationResquestDTO.getPosition());
//            jrOrganization.setMobile(jrOrganizationResquestDTO.getMobile());
//            jrOrganization.setUserId(userId);
//            jrOrganization.setStatus(1);
//            jrOrganizationRepo.save(jrOrganization);
//            vResult.setSuccess(true);
//        } catch (Exception e) {
//            logger.error("失败", e);
//        }
//        return vResult;
//    }


//    @PostMapping("/getOrganization")
//    @ResponseBody
//    public VResult<?> getOrganization(HttpServletRequest request) {
//        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
//        try {
//            Long userId = JwtUtils.getUserId(request.getHeader("token"));
//            List<JrOrganization> jrOrganizations = jrOrganizationRepo.findByUserId(userId);
//            List<JrOrganizationResponseDTO> jrOrganizationResponseDTOList = getOrganizationTree(jrOrganizations);
//            vResult.setContents(jrOrganizationResponseDTOList);
//            vResult.setSuccess(true);
//        } catch (Exception e) {
//            logger.error("失败", e);
//        }
//        return vResult;
//    }
//
//    private List<JrOrganizationResponseDTO> getOrganizationTree(List<JrOrganization> list) {
//        List<JrOrganizationResponseDTO> responseDTOS = new ArrayList<>();
//        for (JrOrganization jrOrganization : list) {
//            if (jrOrganization.getParentId() == null) {
//                JrOrganizationResponseDTO responseDTO = mapper(jrOrganization);
//                Long parentId = jrOrganization.getId();
//                setChildren(responseDTO, parentId, list);
//                responseDTOS.add(responseDTO);
//            }
//        }
//        return responseDTOS;
//    }
//
//    public static JrOrganizationResponseDTO mapper(JrOrganization jrOrganization) {
//        if (jrOrganization == null) {
//            return null;
//        }
//        JrOrganizationResponseDTO jrOrganizationResponseDTO = new JrOrganizationResponseDTO();
//        jrOrganizationResponseDTO.setParentId(jrOrganization.getParentId());
//        jrOrganizationResponseDTO.setName(jrOrganization.getName());
//        jrOrganizationResponseDTO.setGender(jrOrganization.getGender());
//        jrOrganizationResponseDTO.setDepartment(jrOrganization.getDepartment());
//        jrOrganizationResponseDTO.setLevel(jrOrganization.getLevel());
//        jrOrganizationResponseDTO.setPosition(jrOrganization.getPosition());
//        jrOrganizationResponseDTO.setMobile(jrOrganization.getMobile());
//        return jrOrganizationResponseDTO;
//    }
//
//    private void setChildren(JrOrganizationResponseDTO responseDTO, Long parentId, List<JrOrganization> list) {
//        List<JrOrganizationResponseDTO> children = new ArrayList<>();
//        for (JrOrganization jrOrganization : list) {
//            if (parentId.equals(jrOrganization.getParentId())) {
//                JrOrganizationResponseDTO child = mapper(jrOrganization);
//                Long childrenParentId = jrOrganization.getId();
//                setChildren(child, childrenParentId, list);
//                children.add(child);
//                responseDTO.setChildren(children);
//            }
//        }
//    }


    @PostMapping("/addUser")
    @ResponseBody
    public VResult<?> addUser(@RequestBody JrUser jrUser, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            jrUser.setCreateUserId(userId);
            jrUserRepo.save(jrUser);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryUserOrganization")
    @ResponseBody
    public VResult<?> queryUserOrganization(@RequestBody JrQueryUserDTO jrUser, HttpServletRequest request) {
        VResult<JrQuerySingleUserResponseDTO> vResult = new VResult<>(false);
        try {
            List<JrUser> jrUserAllList = null;
            List<JrQuerySingleUserResponseDTO> list = new ArrayList<>();
            if (jrUser.getJrId() != null && jrUser.getJrId() == 999L) {
                jrUserAllList = jrUserRepo.findByJrId(999L);
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO1.setPositionName("主任");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO2.setPositionName("副主任");
                String name1 = "";
                String name2 = "";
                for (JrUser jrUserLevel : jrUserAllList) {
                    if (jrUserLevel.getPosition() == 1) {
                        name1 = name1 + jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 2) {
                        name2 = name2 + jrUserLevel.getUserName() + ",";
                    }
                }
                jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
                jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
                list.add(jrQuerySingleUserResponseDTO1);
                list.add(jrQuerySingleUserResponseDTO2);
            } else if (jrUser.getJrId() != null && jrUser.getJrId() == 888L) {
                jrUserAllList = jrUserRepo.findByJrId(888L);
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO1.setPositionName("主任");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO2.setPositionName("副主任");
                String name1 = "";
                String name2 = "";
                for (JrUser jrUserLevel : jrUserAllList) {
                    if (jrUserLevel.getPosition() == 1) {
                        name1 = name1 + jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 2) {
                        name2 = name2 + jrUserLevel.getUserName() + ",";
                    }
                }
                jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
                jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
                list.add(jrQuerySingleUserResponseDTO1);
                list.add(jrQuerySingleUserResponseDTO2);
            } else if (jrUser.getJrId() != null && jrUser.getJrId() == 777L) {
                jrUserAllList = jrUserRepo.findByJrId(777L);
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO1.setPositionName("领导");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO2.setPositionName("副领导");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO3 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO3.setPositionName("人员");
                String name1 = "";
                String name2 = "";
                String name3 = "";
                for (JrUser jrUserLevel : jrUserAllList) {
                    if (jrUserLevel.getPosition() == 1) {
                        name1 = name1 + jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 2) {
                        name2 = name2 + jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 3) {
                        name3 = name3 + jrUserLevel.getUserName() + ",";
                    }
                }
                jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
                jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
                jrQuerySingleUserResponseDTO3.setUserName(StringUtils.isBlank(name3) ? "" : name3.substring(0, name3.length() - 1));
                list.add(jrQuerySingleUserResponseDTO1);
                list.add(jrQuerySingleUserResponseDTO2);
                list.add(jrQuerySingleUserResponseDTO3);
            } else if (jrUser.getCenterId() != null) {
                jrUserAllList = jrUserRepo.findByCenterId(jrUser.getCenterId());
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO1.setPositionName("主任");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO2.setPositionName("副主任");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO3 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO3.setPositionName("工作人员");
                String name1 = "";
                String name2 = "";
                String name3 = "";
                for (JrUser jrUserLevel : jrUserAllList) {
                    if (jrUserLevel.getPosition() == 1) {
                        name1 = name1 + jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 2) {
                        name2 = name2 + jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 3) {
                        name3 = name3 + jrUserLevel.getUserName() + ",";
                    }
                }
                jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
                jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
                jrQuerySingleUserResponseDTO3.setUserName(StringUtils.isBlank(name3) ? "" : name3.substring(0, name3.length() - 1));
                list.add(jrQuerySingleUserResponseDTO1);
                list.add(jrQuerySingleUserResponseDTO2);
                list.add(jrQuerySingleUserResponseDTO3);
            } else if (jrUser.getOfficeId() != null) {
                jrUserAllList = jrUserRepo.findByOfficeId(jrUser.getOfficeId());
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO1.setPositionName("组长");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO2.setPositionName("工作人员");
                String name1 = "";
                String name2 = "";
                for (JrUser jrUserLevel : jrUserAllList) {
                    if (jrUserLevel.getPosition() == 1) {
                        name1 = name1 + jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 2) {
                        name2 = name2 + jrUserLevel.getUserName() + ",";
                    }
                }
                jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
                jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
                list.add(jrQuerySingleUserResponseDTO1);
                list.add(jrQuerySingleUserResponseDTO2);
            } else if (jrUser.getTownId() != null) {
                jrUserAllList = jrUserRepo.findByTownId(jrUser.getTownId());
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO1.setPositionName("所长");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO2.setPositionName("副所长");
                String name1 = "";
                String name2 = "";
                for (JrUser jrUserLevel : jrUserAllList) {
                    if (jrUserLevel.getPosition() == 1) {
                        name1 = name1 + jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 2) {
                        name2 = name2 + jrUserLevel.getUserName() + ",";
                    }
                }
                jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
                jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
                list.add(jrQuerySingleUserResponseDTO1);
                list.add(jrQuerySingleUserResponseDTO2);
            }
            vResult.setContents(list);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/queryUserList")
    @ResponseBody
    public VResult<?> queryUserList(@RequestBody JrQueryUserDTO jrUser, HttpServletRequest request) {
        VResult<JrQueryUserResponseDTO> vResult = new VResult<>(false);
        try {
            Pageable pageAble = new PageRequest(jrUser.getPageNum() - 1, jrUser.getPageSize());
            JrQueryUserResponseDTO jrQueryUserResponseDTO = new JrQueryUserResponseDTO();
            Page<JrUser> jrUserList = null;
            if (jrUser.getJrId() != null && jrUser.getJrId() == 999L) {
                jrUserList = jrUserRepo.findByJrId(999L, pageAble);
            } else if (jrUser.getJrId() != null && jrUser.getJrId() == 888L) {
                jrUserList = jrUserRepo.findByJrId(888L, pageAble);
            } else if (jrUser.getJrId() != null && jrUser.getJrId() == 777L) {
                jrUserList = jrUserRepo.findByJrId(777L, pageAble);
            } else if (jrUser.getCenterId() != null) {
                jrUserList = jrUserRepo.findByCenterId(jrUser.getCenterId(), pageAble);
            } else if (jrUser.getOfficeId() != null) {
                jrUserList = jrUserRepo.findByOfficeId(jrUser.getOfficeId(), pageAble);
            } else if (jrUser.getTownId() != null) {
                jrUserList = jrUserRepo.findByTownId(jrUser.getTownId(), pageAble);
            } else if (jrUser.getTownRoomId() != null) {
                jrUserList = jrUserRepo.findByTownRoomId(jrUser.getTownRoomId(), pageAble);
            }
            jrQueryUserResponseDTO.setJrUserList(jrUserList);
            vResult.setContent(jrQueryUserResponseDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/editorUser")
    @ResponseBody
    public VResult<?> editorUser(@RequestBody JrUser jrUser, HttpServletRequest request) {
        VResult<JrQueryUserResponseDTO> vResult = new VResult<>(false);
        try {
            Long userId = JwtUtils.getUserId(request.getHeader("token"));
            JrUser jrUserOri = jrUserRepo.findById(jrUser.getId());
            jrUserOri.setUserName(jrUser.getUserName());
            jrUserOri.setGender(jrUser.getGender());
            jrUserOri.setPosition(jrUser.getPosition());
            jrUserOri.setUpdateUserId(userId);
            jrUserRepo.save(jrUserOri);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/delUser")
    @ResponseBody
    public VResult<?> delUser(Long id, HttpServletRequest request) {
        VResult<JrOrganizationResponseDTO> vResult = new VResult<>(false);
        try {
            JrUser jrUser = jrUserRepo.findById(id);
            jrUserRepo.delete(jrUser);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }
}
