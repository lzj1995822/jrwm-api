package com.zkf.mysql.repository;

import com.zkf.mysql.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("systemUserRepo")
public interface SystemUserRepo extends PagingAndSortingRepository<SysUser, Integer> {
	
//    List<SysUser> findAll();
    
	SysUser findByUserId(String userId);

    List<SysUser> findByRoleId(String roleId);

    SysUser findByUserName(String userName);

    @Query("select m from  SysUser m order by m.createTime desc")
    Page<SysUser> findByTime(Pageable pageAble);

    @Query("select m from  SysUser m where m.userId = :userName order by m.createTime desc")
    Page<SysUser> findByUserName(@Param("userName")String userName, Pageable pageAble);

    @Query("select m from  SysUser m where m.roleId = :roleId order by m.createTime desc")
    Page<SysUser> findByRoleId(@Param("roleId")String roleId, Pageable pageAble);

    @Query("select m from  SysUser m where m.roleId = :roleId and m.userId = :userName order by m.createTime desc")
    Page<SysUser> findByRoleIdAndName(@Param("roleId")String roleId, @Param("userName")String userName, Pageable pageAble);
//
//	List<SysUser> findByUserName(String userName);
//
//	SysUser findByUserIdAndUserName(String userId, String userName);
//
	SysUser findByUserIdAndUserPwd(String userId, String password);
//
//	@Transactional
//    @Modifying
//    @Query("update SysUser set lastLoginTime = ?1, lastLoginIp = ?2 where userId = ?3")
//    public int changeLoginTimeAndIp(Timestamp lastLoginTime, String lastLoginIp, String userId);

	@Transactional
    @Modifying
    @Query("update SysUser set userPwd = ?1 where userId = ?2")
    public int changeUserPwd(String userPwd, String userId);

//	@SuppressWarnings("unchecked")
//    public SysUser save(SysUser sysUser);

	@Transactional
    @Modifying
    @Query("update SysUser set userName = ?1, modules = ?2, roleId = ?3 , storesId = ?5 where userId = ?4")
    public int changeForSystemManage(String userName, String modules, String roleId, String userId, Integer storesId);

//	public void delete(SysUser sysUser);
//
//	@Transactional
//    @Modifying
//    @Query("update SysUser set modules = ?1 where userId = ?2")
//    public int changeModules(String modules, String userId);
}
