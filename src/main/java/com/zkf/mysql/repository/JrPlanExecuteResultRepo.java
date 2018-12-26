package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrPlanExecuteResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrPlanExecuteResultRepo")
public interface JrPlanExecuteResultRepo extends JpaRepository<JrPlanExecuteResult, Integer> , JpaSpecificationExecutor<JrPlanExecuteResult> {

    JrPlanExecuteResult findById(Long id);

    Page<JrPlanExecuteResult> findByCheckStatusAndCheckCenterId(Integer checkStatus, Long checkCenterId, Pageable pageable);

    Page<JrPlanExecuteResult> findByCheckStatusAndCheckTownRoomId(Integer checkStatus, Long checkTownRoomId, Pageable pageable);

    List<JrPlanExecuteResult> findByCountryId(Long countryId);

    List<JrPlanExecuteResult> findByCountryIdAndExecuteStatus(Long countryId, Integer executeStatus);

    List<JrPlanExecuteResult> findByCountryIdAndFeature(Long countryId, Integer feature);

    JrPlanExecuteResult findByPlanIdAndCountryId(Long planId, Long countryId);

    List<JrPlanExecuteResult> findByPlanId(Long planId);

    List<JrPlanExecuteResult> findByFeature(Integer feature);

    List<JrPlanExecuteResult> findByFeatureAndPlanIdIn(Integer feature, List<Long> planIds);

    List<JrPlanExecuteResult> findByCheckTownRoomId(Long townRoomId);

    List<JrPlanExecuteResult> findByCheckCenterId(Long centerId);

    Page<JrPlanExecuteResult> findByCheckTownRoomIdAndPlanId(Long townRoomId, Long planId, Pageable pageable);

    long count();

    long countByCheckStatus(Integer checkStatus);

    long countByFeature(Integer feature);

    long countByCountryIdIn(List<Long> countryIds);

    long countByCheckStatusAndCountryIdIn(Integer checkStatus, List<Long> countryIds);

    long countByFeatureAndCountryIdIn(Integer feature, List<Long> countryIds);

    long countByCountryId(Long countryId);

    long countByCheckStatusAndCountryId(Integer checkStatus, Long countryId);

    long countByFeatureAndCountryId(Integer feature, Long countryId);

    long countByResultType(Integer resultType);
}

