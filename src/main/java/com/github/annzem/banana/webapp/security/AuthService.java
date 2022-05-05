package com.github.annzem.banana.webapp.security;

import com.github.annzem.banana.webapp.model.Token;
import com.github.annzem.banana.webapp.model.User;
import com.github.annzem.banana.webapp.model.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token createTokenFor(User user) {
        Token token = new Token(user);
        tokenRepository.saveAndFlush(token);
        return token;
    }
}

