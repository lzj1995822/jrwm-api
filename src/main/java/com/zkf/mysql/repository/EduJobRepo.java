package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduJob;
import com.zkf.mysql.entity.EduOrganization;
import com.zkf.mysql.entity.EduStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


@Repository("eduJobRepo")
public interface EduJobRepo extends JpaRepository<EduJob, Integer> {

    @Query("select m from  EduJob m where m.stick is not null order by m.stick desc")
    List<EduJob> findByStick();

    @Query("select m from  EduJob m where m.stick is null  order by m.createTime desc")
    List<EduJob> findByCreateTime();

    EduJob findById(Long id);

    List<EduJob> findByStatusAndIdIn(Integer status, List<Long> ids);

    @Transactional
    @Modifying
    @Query("update EduJob set status = ?1 where id = ?2")
    int updateStatus(Integer status, Long id);

    List<EduJob> findByUserId(Long userId);

    @Query("select m from  EduJob m where m.status = ?1 order by m.createTime desc")
    Page<EduJob> findAllByCreateTime(Integer status, Pageable pageAble);

    @Transactional
    @Modifying
    @Query("update EduJob set stick = ?1 where id = ?2")
    int updateStick(Timestamp stick, Long id);

}

