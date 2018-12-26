package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduArea;
import com.zkf.mysql.entity.EduSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("eduSchoolRepo")
public interface EduSchoolRepo extends JpaRepository<EduSchool, Integer> {

    List<EduSchool> findByIdIn(List<Long> ids);

    EduSchool findById(Long id);
}

