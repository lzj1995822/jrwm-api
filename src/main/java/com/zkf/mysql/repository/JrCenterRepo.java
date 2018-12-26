package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduArea;
import com.zkf.mysql.entity.JrCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrCenterRepo")
public interface JrCenterRepo extends JpaRepository<JrCenter, Integer> {

    JrCenter findById(Long id);
}

