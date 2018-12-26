package com.zkf.mysql.repository;

import com.zkf.mysql.entity.RoleMappingFunc;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("roleMappingFuncRepo")
public interface RoleMappingFuncRepo extends PagingAndSortingRepository<RoleMappingFunc, Integer> {

    List<RoleMappingFunc> findByRoleId(String roleId);

    RoleMappingFunc findByRoleIdAndFuncId(String roleId, String funcId);

    @Transactional
    @Modifying
    @Query("update RoleMappingFunc set button = ?1 where id = ?2")
    int updateRoleMappingFunc(String button, Integer id);
}
