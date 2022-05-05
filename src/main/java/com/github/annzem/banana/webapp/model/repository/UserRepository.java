package com.github.annzem.banana.webapp.model.repository;

import com.github.annzem.banana.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM bananauser " +
            " LEFT JOIN token t on bananauser.id = t.user_id " +
            " WHERE t.token_val = :tokenVal", nativeQuery = true)
    Optional<User> findByToken(String tokenVal);

    Optional<User> findByChatId(String chatId);
}
