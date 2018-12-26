package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrTown;
import com.zkf.mysql.entity.JrUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrUserRepo")
public interface JrUserRepo extends JpaRepository<JrUser, Integer> {

    JrUser findByUserId(String userId);

    JrUser findByUserIdAndUserPwd(String userId, String userPwd);

    JrUser findById(Long Id);

    List<JrUser> findByJrId(Long jrId);

    List<JrUser> findByCenterId(Long centerId);

    List<JrUser> findByOfficeId(Long officeId);

    List<JrUser> findByTownId(Long townId);

    List<JrUser> findByCountryId(Long countryId);

    Page<JrUser> findByJrId(Long jrId, Pageable pageAble);

    Page<JrUser> findByCenterId(Long centerId, Pageable pageAble);

    Page<JrUser> findByOfficeId(Long officeId, Pageable pageAble);

    Page<JrUser> findByTownId(Long townId, Pageable pageAble);

    Page<JrUser> findByTownRoomId(Long id, Pageable pageAble);

    Page<JrUser> findByCountryId(Long countryId, Pageable pageAble);
}

