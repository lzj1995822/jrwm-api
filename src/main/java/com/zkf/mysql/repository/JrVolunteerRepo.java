package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrVolunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("jrVolunteerRepo")
public interface JrVolunteerRepo extends JpaRepository<JrVolunteer, Integer> {

    JrVolunteer findById(Long id);
}

