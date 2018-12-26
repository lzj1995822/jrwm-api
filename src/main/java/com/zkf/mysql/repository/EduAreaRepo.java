package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("eduAreaRepo")
public interface EduAreaRepo extends JpaRepository<EduArea, Integer> {

    EduArea findById(Long id);
}

