package com.company.bananaapp.model.repository;

import com.company.bananaapp.model.Habit;
import com.company.bananaapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
