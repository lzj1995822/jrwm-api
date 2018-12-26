package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduJobMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("eduJobMappingRepo")
public interface EduJobMappingRepo extends JpaRepository<EduJobMapping, Integer> {

    List<EduJobMapping> findByUserId(Long userId);

    List<EduJobMapping> findByJobIdAndStatus(Long jobId, Integer status);

    @Transactional
    @Modifying
    @Query("update EduJobMapping set status = ?1 where id = ?2")
    int updateStatus(Integer status, Long id);
}

