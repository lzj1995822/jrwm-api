package com.zkf.mysql.repository;

import com.zkf.mysql.entity.EduCarousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("eduCarouselRepo")
public interface EduCarouselRepo extends JpaRepository<EduCarousel, Integer> {

    EduCarousel findById(Long id);
}

