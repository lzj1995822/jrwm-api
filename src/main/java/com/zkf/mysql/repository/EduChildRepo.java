package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduChild;
import com.zkf.mysql.entity.EduPatriarch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("eduChildRepo")
public interface EduChildRepo extends JpaRepository<EduChild, Integer> {

    List<EduChild> findByPatriarchId(Long patriarchId);

    EduChild findById(Long childId);

    @Query("select m from  EduChild m where m.status = ?1 order by m.createTime desc")
    Page<EduChild> findAllByCreateTime(Integer status, Pageable pageAble);
}

