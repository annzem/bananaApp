package com.github.annzem.banana.webapp.model.repository;

import com.github.annzem.banana.webapp.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenVal(String tokenVal);


}
