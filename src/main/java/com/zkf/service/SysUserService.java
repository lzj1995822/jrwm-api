package com.zkf.service;

import com.zkf.mysql.entity.SysUser;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by zkf on 2017/12/7.
 */
public interface SysUserService {

    Page<SysUser> getSysUser(Integer pageNumber, Integer pagzSize);
}
