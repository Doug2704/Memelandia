package com.candido.categoryservice.repository;

import com.candido.categoryservice.entity.MemeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<MemeCategory, Long> {
}
