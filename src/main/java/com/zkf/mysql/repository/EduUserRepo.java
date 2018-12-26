package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("eduUserRepo")
public interface EduUserRepo extends JpaRepository<EduUser, Integer> {

    EduUser findByMobile(String mobile);

    EduUser findById(Long id);

    EduUser findByMobileAndUserPwd(String mobile, String userPwd);

}

