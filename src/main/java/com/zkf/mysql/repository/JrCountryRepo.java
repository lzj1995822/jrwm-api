package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrCountryRepo")
public interface JrCountryRepo extends JpaRepository<JrCountry, Integer> {

    List<JrCountry> findByTownId(Long townId);

    JrCountry findById(Long id);

    long countByTownId(Long townId);
}

