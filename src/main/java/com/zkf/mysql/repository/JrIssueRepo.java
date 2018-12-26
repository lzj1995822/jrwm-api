package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrCenter;
import com.zkf.mysql.entity.JrIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("jrIssueRepo")
public interface JrIssueRepo extends JpaRepository<JrIssue, Integer> {

    JrIssue findById(Long id);
}

