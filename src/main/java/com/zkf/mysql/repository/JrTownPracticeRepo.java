package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrCenterPractice;
import com.zkf.mysql.entity.JrTownPractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrTownPracticeRepo")
public interface JrTownPracticeRepo extends JpaRepository<JrTownPractice, Integer> {

    List<JrTownPractice> findByTownId(Long townId);
}

