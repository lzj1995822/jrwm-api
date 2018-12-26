package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduCampus;
import com.zkf.mysql.entity.EduSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("eduCampusRepo")
public interface EduCampusRepo extends JpaRepository<EduCampus, Integer> {

    List<EduCampus> findByIdIn(List<Long> ids);
}

