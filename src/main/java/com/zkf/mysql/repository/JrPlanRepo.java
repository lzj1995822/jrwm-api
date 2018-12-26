package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrPlanRepo")
public interface JrPlanRepo extends JpaRepository<JrPlan, Integer> {

    JrPlan findById(Long id);

    List<JrPlan> findBySelectTypeAndCheckStatus(Integer selectType, Integer checkStatus);

    List<JrPlan> findByCenterId(Long centerId);

    List<JrPlan> findByIdIn(List<Long> ids);

    Page<JrPlan> findByIdIn(List<Long> ids, Pageable pageable);

    Page<JrPlan> findBySelectTypeAndIdIn(Integer selectType, List<Long> ids, Pageable pageable);

    Page<JrPlan> findByCenterId(Long centerId, Pageable pageAble);

    List<JrPlan> findByCenterIdIsNotNull();

    Page<JrPlan> findByCountryId(Long countryId, Pageable pageable);

    Page<JrPlan> findByTownRoomIdAndCheckStatus(Long countryId, Integer checkStatus, Pageable pageable);

    Page<JrPlan> findByCenterIdAndCheckStatus(Long centerId, Integer checkStatus, Pageable pageable);

    Page<JrPlan> findBySelectType(Integer selectType, Pageable pageable);
}

