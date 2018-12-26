package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrOrganizationRepo")
public interface JrOrganizationRepo extends JpaRepository<JrOrganization, Integer> {

    List<JrOrganization> findByUserId(Long userId);
}

