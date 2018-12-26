package com.zkf.mysql.repository;

import com.zkf.mysql.entity.JrTownPractice;
import com.zkf.mysql.entity.JrTownRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("jrTownRoomRepo")
public interface JrTownRoomRepo extends JpaRepository<JrTownRoom, Integer> {

    List<JrTownRoom> findByCenterId(Long centerId);

    JrTownRoom findByCenterIdAndTownId(Long centerId, Long townId);

    List<JrTownRoom> findByTownId(Long townId);

    JrTownRoom findById(Long id);
}

