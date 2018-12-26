package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduPatriarch;
import com.zkf.mysql.entity.EduStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("eduPatriarchRepo")
public interface EduPatriarchRepo extends JpaRepository<EduPatriarch, Integer> {

    EduPatriarch findByUserId(Long id);

    @Query("select m from  EduPatriarch m where m.status = ?1 order by m.createTime desc")
    Page<EduPatriarch> findAllByCreateTime(Integer status, Pageable pageAble);

    EduPatriarch findById(Long id);

}

