package com.github.annzem.banana.webapp.security;

import com.github.annzem.banana.webapp.model.Token;
import com.github.annzem.banana.webapp.model.User;
import com.github.annzem.banana.webapp.model.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public class TokenValAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenValAuthorizationToken token = (TokenValAuthorizationToken) authentication;
        Optional<Token> tokenEntity = tokenRepository.findByTokenVal(token.getTokenVal());

        if (tokenEntity.isPresent() &&
            tokenEntity.get().getActive()) {

            User userEntity = tokenEntity.get().getUser();

            String username = userEntity.getEmail();
            UserDetails user = userDetailsService.loadUserByUsername(username);
            tokenEntity.get().setActive(false);
            tokenRepository.saveAndFlush(tokenEntity.get());
            return createSuccessAuthentication(user, authentication, user);
        } else {
            throw new BadCredentialsException("wrong token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenValAuthorizationToken.class.isAssignableFrom(authentication);
    }
}
