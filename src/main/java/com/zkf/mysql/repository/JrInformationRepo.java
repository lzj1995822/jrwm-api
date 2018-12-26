package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("jrInformationRepo")
public interface JrInformationRepo extends JpaRepository<JrInformation, Integer> {

    JrInformation findById(Long id);

    Page<JrInformation> findAll(Pageable pageAble);
}

