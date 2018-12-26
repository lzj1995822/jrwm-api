package com.zkf.mysql.repository;

import com.zkf.mysql.entity.Func;
import com.zkf.mysql.entity.Module;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("funcRepo")
public interface FuncRepo extends PagingAndSortingRepository<Func, Integer> {

    List<Func> findByModuleId(String moduleId);

    Func findById(Integer id);

    Func findByFuncId(String id);
}
