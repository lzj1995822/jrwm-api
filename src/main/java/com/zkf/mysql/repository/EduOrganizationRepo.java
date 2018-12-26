package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduOrganization;
import com.zkf.mysql.entity.EduPatriarch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("eduOrganizationRepo")
public interface EduOrganizationRepo extends JpaRepository<EduOrganization, Integer> {

    EduOrganization findByUserId(Long userId);

    EduOrganization findById(Long id);

    @Transactional
    @Modifying
    @Query("update EduOrganization set mobile = ?1, name = ?2 , headPic = ?3 , contactPerson = ?4 , area = ?5 , address = ?6 , trainItem = ?7 , introduction = ?8 , fullTime = ?9 , partTime = ?10 where userId = ?11")
    int update(String mobile, String name, String headPic, String contactPerson, Integer area, String address,
               String trainItem, String introduction, Integer fullTime, Integer partTime, Long userId);

    @Query("select m from  EduOrganization m where m.status = ?1 order by m.createTime desc")
    Page<EduOrganization> findAllByCreateTime(Integer status, Pageable pageAble);
}

