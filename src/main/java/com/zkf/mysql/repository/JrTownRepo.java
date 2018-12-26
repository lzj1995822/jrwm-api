package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrPlan;
import com.zkf.mysql.entity.JrTown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("jrTownRepo")
public interface JrTownRepo extends JpaRepository<JrTown, Integer> {

    JrTown findById(Long id);
}

