package com.zkf.mysql.repository;

import com.zkf.mysql.entity.Module;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("moduleRepo")
public interface ModuleRepo extends PagingAndSortingRepository<Module, Integer> {

    List<Module> findAll();

}
