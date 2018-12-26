package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.List;


@Repository("eduStudentRepo")
public interface EduStudentRepo extends JpaRepository<EduStudent, Integer> {

    @Query("select m from  EduStudent m order by m.createTime desc")
    Page<EduStudent> findAllPage(Pageable pageAble);

    @Query("select m from  EduStudent m where m.stick is not null order by m.stick desc")
    List<EduStudent> findByStick();

    @Query("select m from  EduStudent m where m.starLevel = :starLevel and m.stick is null  order by m.createTime desc")
    List<EduStudent> findByStarLevel(@Param("starLevel")Integer starLevel);

    EduStudent findByUserId(Long userId);

    EduStudent findById(Long id);

    @Query("select m from  EduStudent m where m.status = ?1 order by m.createTime desc")
    Page<EduStudent> findAllByCreateTime(Integer status, Pageable pageAble);

    @Transactional
    @Modifying
    @Query("update EduStudent set stick = ?1 where id = ?2")
    int updateStick(Timestamp stick,  Long id);

    @Transactional
    @Modifying
    @Query("update EduStudent set platformPrize = ?1 where id = ?2")
    int addPlatformPrize(Integer platformPrize,  Long id);

}

