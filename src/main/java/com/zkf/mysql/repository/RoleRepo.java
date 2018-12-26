package com.zkf.mysql.repository;

import com.zkf.mysql.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository("roleRepo")
public interface RoleRepo extends PagingAndSortingRepository<Role, Integer> {

    Role findById(Integer id);

    List<Role> findAll();

    @Query("select m from  Role m where m.roleName = :hpTitle and m.createTime >= :beginTime and m.createTime < :endTime order by m.createTime desc")
    Page<Role> findByTime(@Param("hpTitle") String hpTitle, @Param("beginTime") Timestamp beginTime, @Param("endTime") Timestamp endTime, Pageable pageAble);

    @Query("select m from  Role m order by m.createTime desc")
    Page<Role> findByTime(Pageable pageAble);

    @Query("select m from  Role m where m.roleName = :hpTitle order by m.createTime desc")
    Page<Role> findByTime(@Param("hpTitle") String hpTitle, Pageable pageAble);

    @Query("select m from  Role m where m.createTime >= :beginTime and m.createTime < :endTime order by m.createTime desc")
    Page<Role> findByTime(@Param("beginTime") Timestamp beginTime, @Param("endTime") Timestamp endTime, Pageable pageAble);
}
