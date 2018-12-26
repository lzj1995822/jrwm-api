package com.zkf.service.impl;

import com.zkf.mysql.entity.SysUser;
import com.zkf.mysql.repository.SystemUserRepo;
import com.zkf.service.SysUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zkf on 2017/12/7.
 */
@Service("sysUserServiceImpl")
public class SysUserServiceImpl implements SysUserService {

    @Resource(name = "systemUserRepo")
    private SystemUserRepo systemUserRepo;

    @Override
    public Page<SysUser> getSysUser(Integer pageNumber, Integer pagzSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pagzSize, null);
        Page<SysUser> sourceCodes= systemUserRepo.findAll(request);
        return sourceCodes;
    }
}
