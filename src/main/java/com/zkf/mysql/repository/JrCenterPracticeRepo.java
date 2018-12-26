package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrCenterPractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrCenterPracticeRepo")
public interface JrCenterPracticeRepo extends JpaRepository<JrCenterPractice, Integer> {

    List<JrCenterPractice> findByCenterId(Long centerId);
}

