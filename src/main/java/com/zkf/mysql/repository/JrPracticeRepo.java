package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrPractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrPracticeRepo")
public interface JrPracticeRepo extends JpaRepository<JrPractice, Integer> {

    List<JrPractice> findByCountryId(Long countryId);

    List<JrPractice> findByCenterId(Long centerId);

    List<JrPractice> findByTownRoomId(Long townRoomId);

    JrPractice findById(Long id);

    long countByCenterId(Long centerId);
}

