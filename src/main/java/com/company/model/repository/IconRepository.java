package com.company.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.model.Icon;


public interface IconRepository extends JpaRepository<Icon, Long> {
    Icon findByTitle(String title);
}
