package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduInformatization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("eduInformationRepo")
public interface EduInformationRepo extends JpaRepository<EduInformatization, Integer> {

    @Query("select m.id, m.imageUrl, m.title, m.createTime, m.updateTime from  EduInformatization m where m.status = ?1  order by m.createTime desc")
    List<Object[]> findAll(Integer status);

    EduInformatization findById(Long id);

}

