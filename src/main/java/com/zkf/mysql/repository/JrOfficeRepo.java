package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrCountry;
import com.zkf.mysql.entity.JrOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("jrOfficeRepo")
public interface JrOfficeRepo extends JpaRepository<JrOffice, Integer> {

    JrOffice findById(Long id);
}

