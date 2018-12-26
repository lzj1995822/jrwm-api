package com.zkf.controller;

import com.common.utils.JwtUtils;
import com.zkf.model.VResult;
import com.zkf.mysql.entity.*;
import com.zkf.mysql.repository.*;
import com.zkf.pojo.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JrFrontController {

    private static final Logger logger = LoggerFactory.getLogger(JrFrontController.class);

    @Resource
    private JrOrganizationRepo jrOrganizationRepo;

    @Resource
    private JrCenterRepo jrCenterRepo;

    @Resource
    private JrTownRepo jrTownRepo;

    @Resource
    private JrCenterPracticeRepo jrCenterPracticeRepo;

    @Resource
    private JrCountryRepo jrCountryRepo;

    @Resource
    private JrOfficeRepo jrOfficeRepo;

    @Resource
    private JrUserRepo jrUserRepo;

    @Resource
    private JrPlanRepo jrPlanRepo;

    @Resource
    private JrPlanExecuteResultRepo jrPlanExecuteResultRepo;

    @Resource
    private JrPracticeRepo jrPracticeRepo;

    @PostMapping("/getLeftInfoForJRCenter")
    @ResponseBody
    public VResult<?> getLeftInfoForJRCenter() {
        VResult<JrLeftInfoForJRCenter> vResult = new VResult<>(false);
        try {
            JrLeftInfoForJRCenter jrLeftInfoForJRCenter = new JrLeftInfoForJRCenter();
            long planResultTotal = jrPlanExecuteResultRepo.count();
            long passPlanResultTotal = jrPlanExecuteResultRepo.countByCheckStatus(3);
            long selfPlanTotal = jrPlanExecuteResultRepo.countByResultType(2);
            long featureTotal = jrPlanExecuteResultRepo.countByFeature(1);
            jrLeftInfoForJRCenter.setPlanResultTotal(planResultTotal);
            jrLeftInfoForJRCenter.setPassPlanResultTotal(passPlanResultTotal);
            jrLeftInfoForJRCenter.setSelfPlanTotal(selfPlanTotal);
            jrLeftInfoForJRCenter.setFeatureRadio(featureTotal*100/planResultTotal);
            jrLeftInfoForJRCenter.setCommonRadio(100 - featureTotal*100/planResultTotal);

            List<JrLeftPracticeDTO> practiceDTO = new ArrayList<>();
            List<JrCenter> jrCenters = jrCenterRepo.findAll();
            for(JrCenter jrCenter : jrCenters){
                JrLeftPracticeDTO jrLeftPracticeDTO = new JrLeftPracticeDTO();
                jrLeftPracticeDTO.setCenterName(jrCenter.getName());
                jrLeftPracticeDTO.setCount(jrPracticeRepo.countByCenterId(jrCenter.getId()));
                practiceDTO.add(jrLeftPracticeDTO);
            }
            jrLeftInfoForJRCenter.setPracticeDTO(practiceDTO);

            vResult.setContent(jrLeftInfoForJRCenter);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/getCountryCount")
    @ResponseBody
    public VResult<?> getCountryCount() {
        VResult<JrCountryCountDTO> vResult = new VResult<>(false);
        try {
            List<JrCountryCountDTO> jrCountryCountDTOS = new ArrayList<>();
            List<JrTown> jrTowns = jrTownRepo.findAll();
            for(JrTown jrTown : jrTowns){
                JrCountryCountDTO jrCountryCountDTO = new JrCountryCountDTO();
                jrCountryCountDTO.setTownName(jrTown.getName());
                jrCountryCountDTO.setLon(jrTown.getLon());
                jrCountryCountDTO.setLat(jrTown.getLat());
                jrCountryCountDTO.setTownId(jrTown.getId());
                jrCountryCountDTO.setCountryCount(jrCountryRepo.countByTownId(jrTown.getId()));
                jrCountryCountDTOS.add(jrCountryCountDTO);
            }
            vResult.setContents(jrCountryCountDTOS);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/getRightInfoForTown")
    @ResponseBody
    public VResult<?> getRightInfoForTown(Long townId) {
        VResult<JrRightInfoForJRCenter> vResult = new VResult<>(false);
        try {
            List<JrCountry> jrCountryList = jrCountryRepo.findByTownId(townId);
            List<Long> countryIds = jrCountryList.stream().map(j -> j.getId()).collect(Collectors.toList());
            JrRightInfoForJRCenter jrRightInfoForJRCenter = new JrRightInfoForJRCenter();
            long planResultTotal = jrPlanExecuteResultRepo.countByCountryIdIn(countryIds);
            long passPlanResultTotal = jrPlanExecuteResultRepo.countByCheckStatusAndCountryIdIn(3, countryIds);
            long featureTotal = jrPlanExecuteResultRepo.countByFeatureAndCountryIdIn(1, countryIds);
            jrRightInfoForJRCenter.setPlanResultTotal(planResultTotal);
            jrRightInfoForJRCenter.setPassPlanResultTotal(passPlanResultTotal);
            jrRightInfoForJRCenter.setRadio(featureTotal*100/planResultTotal);
            vResult.setContent(jrRightInfoForJRCenter);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/getRightInfoForCountry")
    @ResponseBody
    public VResult<?> getRightInfoForCountry(Long countryId) {
        VResult<JrRightInfoForJRCenter> vResult = new VResult<>(false);
        try {
            JrRightInfoForJRCenter jrRightInfoForJRCenter = new JrRightInfoForJRCenter();
            long planResultTotal = jrPlanExecuteResultRepo.countByCountryId(countryId);
            long passPlanResultTotal = jrPlanExecuteResultRepo.countByCheckStatusAndCountryId(3, countryId);
            long featureTotal = jrPlanExecuteResultRepo.countByFeatureAndCountryId(1, countryId);
            jrRightInfoForJRCenter.setPlanResultTotal(planResultTotal);
            jrRightInfoForJRCenter.setPassPlanResultTotal(passPlanResultTotal);
            jrRightInfoForJRCenter.setRadio(featureTotal*100/planResultTotal);
            vResult.setContent(jrRightInfoForJRCenter);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }


    @PostMapping("/getInfoForJRCenter")
    @ResponseBody
    public VResult<?> getInfoForJRCenter() {
        VResult<JrFrontMainInfoDTO> vResult = new VResult<>(false);
        try {
            JrFrontMainInfoDTO jrFrontMainInfoDTO = new JrFrontMainInfoDTO();
            jrFrontMainInfoDTO.setJrCenterList(jrCenterRepo.findAll());
            List<JrTown> jrTowns = jrTownRepo.findAll();
            jrFrontMainInfoDTO.setJrTownList(jrTowns);
            //组织架构
            List<JrUser> jrUserList = jrUserRepo.findByJrId(999L);
            List<JrQuerySingleUserResponseDTO> list = new ArrayList<>();
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO1.setPositionName("主任");
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO2.setPositionName("副主任");
            String name1 = "";
            String name2 = "";
            for (JrUser jrUserLevel : jrUserList) {
                if (jrUserLevel.getPosition() == 1) {
                    name1 = jrUserLevel.getUserName() + ",";
                } else if (jrUserLevel.getPosition() == 2) {
                    name2 = jrUserLevel.getUserName() + ",";
                }
            }
            jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
            jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
            list.add(jrQuerySingleUserResponseDTO1);
            list.add(jrQuerySingleUserResponseDTO2);
            jrFrontMainInfoDTO.setJrQuerySingleUserResponseDTOList(list);
            //办公室
            List<JrOffice> jrOfficeList = jrOfficeRepo.findAll();
            List<JrOfficeFrontDTO> jrOfficeFrontDTOList = new ArrayList<>();
            for (JrOffice jrOffice : jrOfficeList) {
                JrOfficeFrontDTO jrOfficeFrontDTO = new JrOfficeFrontDTO();
                jrOfficeFrontDTO.setName(jrOffice.getName());
                jrOfficeFrontDTO.setContent(jrOffice.getContent());
                jrUserList = jrUserRepo.findByOfficeId(jrOffice.getId());
                List<JrQuerySingleUserResponseDTO> responseDTOS = new ArrayList<>();
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1_ = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO1_.setPositionName("组长");
                JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2_ = new JrQuerySingleUserResponseDTO();
                jrQuerySingleUserResponseDTO2_.setPositionName("工作人员");
                String name1_ = "";
                String name2_ = "";
                for (JrUser jrUserLevel : jrUserList) {
                    if (jrUserLevel.getPosition() == 1) {
                        name1_ = jrUserLevel.getUserName() + ",";
                    } else if (jrUserLevel.getPosition() == 2) {
                        name2_ = jrUserLevel.getUserName() + ",";
                    }
                }
                jrQuerySingleUserResponseDTO1_.setUserName(StringUtils.isBlank(name1_) ? "" : name1_.substring(0, name1_.length() - 1));
                jrQuerySingleUserResponseDTO2_.setUserName(StringUtils.isBlank(name2_) ? "" : name2_.substring(0, name2_.length() - 1));
                responseDTOS.add(jrQuerySingleUserResponseDTO1_);
                responseDTOS.add(jrQuerySingleUserResponseDTO2_);
                jrOfficeFrontDTO.setJrQuerySingleUserResponseDTOList(responseDTOS);
                jrOfficeFrontDTOList.add(jrOfficeFrontDTO);
            }
            jrFrontMainInfoDTO.setJrOfficeFrontDTOList(jrOfficeFrontDTOList);
            //积分
            List<JrTownIntegralDTO> jrTownIntegralDTOList = new ArrayList<>();
            for(JrTown jrTown : jrTowns){
                JrTownIntegralDTO jrTownIntegralDTO = new JrTownIntegralDTO();
                jrTownIntegralDTO.setName(jrTown.getName());
                jrTownIntegralDTO.setIntegral(jrTown.getIntegral());
                jrTownIntegralDTOList.add(jrTownIntegralDTO);
            }
            jrTownIntegralDTOList = jrTownIntegralDTOList.stream().sorted((o1, o2) -> o2.getIntegral().compareTo(o1.getIntegral())).collect(Collectors.toList());
            jrFrontMainInfoDTO.setJrTownIntegralDTOList(jrTownIntegralDTOList);
            vResult.setContent(jrFrontMainInfoDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/getInfoForCenter")
    @ResponseBody
    public VResult<?> getInfoForCenter(Long centerId) {
        VResult<JrFrontCenterInfoDTO> vResult = new VResult<>(false);
        try {
            JrFrontCenterInfoDTO jrFrontCenterInfoDTO = new JrFrontCenterInfoDTO();
            List<JrUser> jrUserList = jrUserRepo.findByCenterId(centerId);
            List<JrQuerySingleUserResponseDTO> list = new ArrayList<>();
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO1.setPositionName("主任");
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO2.setPositionName("副主任");
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO3 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO3.setPositionName("工作人员");
            String name1 = "";
            String name2 = "";
            String name3 = "";
            for (JrUser jrUserLevel : jrUserList) {
                if (jrUserLevel.getPosition() == 1) {
                    name1 = jrUserLevel.getUserName() + ",";
                } else if (jrUserLevel.getPosition() == 2) {
                    name2 = jrUserLevel.getUserName() + ",";
                } else if (jrUserLevel.getPosition() == 3) {
                    name3 = jrUserLevel.getUserName() + ",";
                }
            }
            jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
            jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
            jrQuerySingleUserResponseDTO3.setUserName(StringUtils.isBlank(name3) ? "" : name3.substring(0, name3.length() - 1));
            list.add(jrQuerySingleUserResponseDTO1);
            list.add(jrQuerySingleUserResponseDTO2);
            list.add(jrQuerySingleUserResponseDTO3);
            jrFrontCenterInfoDTO.setJrQuerySingleUserResponseDTOList(list);
            //计划
            List<JrPlan> jrPlans = jrPlanRepo.findByCenterId(centerId);
            jrFrontCenterInfoDTO.setJrPlanList(jrPlans);
            //特色活动
            List<JrFeatureResultDTO> jrFeatureResultDTOList = new ArrayList<>();
            List<Long> planIds = jrPlans.stream().map(j -> j.getId()).collect(Collectors.toList());
            List<JrPlanExecuteResult> jrPlanExecuteResults = jrPlanExecuteResultRepo.findByFeatureAndPlanIdIn(1, planIds);
            for(JrPlanExecuteResult jrPlanExecuteResult : jrPlanExecuteResults){
                JrFeatureResultDTO jrFeatureResultDTO = new JrFeatureResultDTO();
                JrCountry jrCountry = jrCountryRepo.findById(jrPlanExecuteResult.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
                jrFeatureResultDTO.setCountryName(jrCountry.getName());
                jrFeatureResultDTO.setPic(jrPlanExecuteResult.getResultPic());
                jrFeatureResultDTO.setPlanName(jrPlan.getName());
                jrFeatureResultDTO.setTownName(jrTown.getName());
                jrFeatureResultDTOList.add(jrFeatureResultDTO);
            }
            jrFrontCenterInfoDTO.setJrFeatureResultDTO(jrFeatureResultDTOList);
            vResult.setContent(jrFrontCenterInfoDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    @PostMapping("/getInfoForTown")
    @ResponseBody
    public VResult<?> getInfoForTown(Long townId) {
        VResult<JrFrontCenterInfoDTO> vResult = new VResult<>(false);
        try {
            JrFrontCenterInfoDTO jrFrontCenterInfoDTO = new JrFrontCenterInfoDTO();
            List<JrUser> jrUserList = jrUserRepo.findByTownId(townId);
            List<JrQuerySingleUserResponseDTO> list = new ArrayList<>();
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO1.setPositionName("所长");
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO2.setPositionName("副所长");
            String name1 = "";
            String name2 = "";
            for (JrUser jrUserLevel : jrUserList) {
                if (jrUserLevel.getPosition() == 1) {
                    name1 = jrUserLevel.getUserName() + ",";
                } else if (jrUserLevel.getPosition() == 2) {
                    name2 = jrUserLevel.getUserName() + ",";
                }
            }
            jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
            jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
            list.add(jrQuerySingleUserResponseDTO1);
            list.add(jrQuerySingleUserResponseDTO2);
            jrFrontCenterInfoDTO.setJrQuerySingleUserResponseDTOList(list);

            //镇积分
            JrTownInfoDTO jrTownInfoDTO = new JrTownInfoDTO();
            List<JrTown> jrTowns = jrTownRepo.findAll();
            List<JrTownIntegralDTO> jrTownIntegralDTOList = new ArrayList<>();
            for(JrTown jrTown : jrTowns){
                JrTownIntegralDTO jrTownIntegralDTO = new JrTownIntegralDTO();
                jrTownIntegralDTO.setName(jrTown.getName());
                jrTownIntegralDTO.setIntegral(jrTown.getIntegral());
                jrTownIntegralDTO.setTownId(jrTown.getId());
                jrTownIntegralDTOList.add(jrTownIntegralDTO);
            }
            jrTownIntegralDTOList = jrTownIntegralDTOList.stream().sorted((o1, o2) -> o2.getIntegral().compareTo(o1.getIntegral())).collect(Collectors.toList());
            for (int i = 0; i < jrTownIntegralDTOList.size(); i++) {
                if(townId.equals(jrTownIntegralDTOList.get(i).getTownId())){
                    jrTownInfoDTO.setIntegralTotal(jrTownIntegralDTOList.get(i).getIntegral());
                    jrTownInfoDTO.setRank(i+1);
                }
            }
            List<JrCountry> jrCountryList = jrCountryRepo.findByTownId(townId);
            List<Long> planIds = jrCountryList.stream().map(p -> p.getId()).collect(Collectors.toList());
            List<JrPlan> jrPlans = jrPlanRepo.findByIdIn(planIds);
            List<JrTownIntegralDTO> jrPlanList = new ArrayList<>();
            for(JrPlan jrPlan : jrPlans){
                JrTownIntegralDTO jrTownIntegralDTO = new JrTownIntegralDTO();
                jrTownIntegralDTO.setIntegral(jrPlan.getIntegral());
                jrTownIntegralDTO.setName(jrPlan.getName());
                jrPlanList.add(jrTownIntegralDTO);
            }
            jrTownInfoDTO.setJrTownIntegralDTOList(jrPlanList);
            jrFrontCenterInfoDTO.setJrTownInfoDTO(jrTownInfoDTO);

            //计划
            List<JrPlan> jrPlans1 = jrPlanRepo.findAll();
            jrFrontCenterInfoDTO.setJrPlanList(jrPlans1);
            //特色活动
            List<JrFeatureResultDTO> jrFeatureResultDTOList = new ArrayList<>();
            List<Long> planids = jrPlans1.stream().map(j -> j.getId()).collect(Collectors.toList());
            List<JrPlanExecuteResult> jrPlanExecuteResults = jrPlanExecuteResultRepo.findByFeatureAndPlanIdIn(1, planids);
            for(JrPlanExecuteResult jrPlanExecuteResult : jrPlanExecuteResults){
                JrFeatureResultDTO jrFeatureResultDTO = new JrFeatureResultDTO();
                JrCountry jrCountry = jrCountryRepo.findById(jrPlanExecuteResult.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
                jrFeatureResultDTO.setCountryName(jrCountry.getName());
                jrFeatureResultDTO.setPic(jrPlanExecuteResult.getResultPic());
                jrFeatureResultDTO.setPlanName(jrPlan.getName());
                jrFeatureResultDTO.setTownName(jrTown.getName());
                jrFeatureResultDTOList.add(jrFeatureResultDTO);
            }
            jrFrontCenterInfoDTO.setJrFeatureResultDTO(jrFeatureResultDTOList);
            vResult.setContent(jrFrontCenterInfoDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }


    @PostMapping("/getInfoForCountry")
    @ResponseBody
    public VResult<?> getInfoForCountry(Long cuntryId) {
        VResult<JrFrontCenterInfoDTO> vResult = new VResult<>(false);
        try {
            JrFrontCenterInfoDTO jrFrontCenterInfoDTO = new JrFrontCenterInfoDTO();
            List<JrUser> jrUserList = jrUserRepo.findByCountryId(cuntryId);
            List<JrQuerySingleUserResponseDTO> list = new ArrayList<>();
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO1 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO1.setPositionName("所长");
            JrQuerySingleUserResponseDTO jrQuerySingleUserResponseDTO2 = new JrQuerySingleUserResponseDTO();
            jrQuerySingleUserResponseDTO2.setPositionName("工作人员");
            String name1 = "";
            String name2 = "";
            for (JrUser jrUserLevel : jrUserList) {
                if (jrUserLevel.getPosition() == 1) {
                    name1 = jrUserLevel.getUserName() + ",";
                } else if (jrUserLevel.getPosition() == 2) {
                    name2 = jrUserLevel.getUserName() + ",";
                }
            }
            jrQuerySingleUserResponseDTO1.setUserName(StringUtils.isBlank(name1) ? "" : name1.substring(0, name1.length() - 1));
            jrQuerySingleUserResponseDTO2.setUserName(StringUtils.isBlank(name2) ? "" : name2.substring(0, name2.length() - 1));
            list.add(jrQuerySingleUserResponseDTO1);
            list.add(jrQuerySingleUserResponseDTO2);
            jrFrontCenterInfoDTO.setJrQuerySingleUserResponseDTOList(list);
            //计划
            List<JrPlan> jrPlans1 = jrPlanRepo.findAll();
            jrFrontCenterInfoDTO.setJrPlanList(jrPlans1);
            //特色活动
            List<JrFeatureResultDTO> jrFeatureResultDTOList = new ArrayList<>();
            List<Long> planids = jrPlans1.stream().map(j -> j.getId()).collect(Collectors.toList());
            List<JrPlanExecuteResult> jrPlanExecuteResults = jrPlanExecuteResultRepo.findByFeatureAndPlanIdIn(1, planids);
            for(JrPlanExecuteResult jrPlanExecuteResult : jrPlanExecuteResults){
                JrFeatureResultDTO jrFeatureResultDTO = new JrFeatureResultDTO();
                JrCountry jrCountry = jrCountryRepo.findById(jrPlanExecuteResult.getCountryId());
                JrTown jrTown = jrTownRepo.findById(jrCountry.getTownId());
                JrPlan jrPlan = jrPlanRepo.findById(jrPlanExecuteResult.getPlanId());
                jrFeatureResultDTO.setCountryName(jrCountry.getName());
                jrFeatureResultDTO.setPic(jrPlanExecuteResult.getResultPic());
                jrFeatureResultDTO.setPlanName(jrPlan.getName());
                jrFeatureResultDTO.setTownName(jrTown.getName());
                jrFeatureResultDTOList.add(jrFeatureResultDTO);
            }
            jrFrontCenterInfoDTO.setJrFeatureResultDTO(jrFeatureResultDTOList);
            vResult.setContent(jrFrontCenterInfoDTO);
            vResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("失败", e);
        }
        return vResult;
    }

    //获取分中心实践点
//    @PostMapping("/getInfoForCenter")
//    @ResponseBody
//    public VResult<?> getCenterPractice(Long centerId) {
//        VResult<JrCenterPractice> vResult = new VResult<>(false);
//        try {
//            List<JrCenterPractice> jrCenterPracticeList = jrCenterPracticeRepo.findByCenterId(centerId);
//            vResult.setContents(jrCenterPracticeList);
//            vResult.setSuccess(true);
//        } catch (Exception e) {
//            logger.error("失败", e);
//        }
//        return vResult;
//    }
//
//    //获取村
//    @PostMapping("/getCountry")
//    @ResponseBody
//    public VResult<?> getCountry(Long townId) {
//        VResult<JrCountry> vResult = new VResult<>(false);
//        try {
//            List<JrCountry> jrCountryList = jrCountryRepo.findByTownId(townId);
//            vResult.setContents(jrCountryList);
//            vResult.setSuccess(true);
//        } catch (Exception e) {
//            logger.error("失败", e);
//        }
//        return vResult;
//    }
//
//    //组织架构
//    @PostMapping("/getJrOrganization")
//    @ResponseBody
//    public VResult<?> getJrOrganization() {
//        VResult<JrOrganizationFrontDTO> vResult = new VResult<>(false);
//        try {
//            JrOrganizationFrontDTO jrOrganizationFrontDTO = new JrOrganizationFrontDTO();
//            jrOrganizationFrontDTO.setJrCenterList(jrCenterRepo.findAll());
//            jrOrganizationFrontDTO.setJrOfficeList(jrOfficeRepo.findAll());
//            vResult.setContent(jrOrganizationFrontDTO);
//            vResult.setSuccess(true);
//        } catch (Exception e) {
//            logger.error("失败", e);
//        }
//        return vResult;
//    }

}
