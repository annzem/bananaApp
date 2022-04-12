package com.github.annzem.banana.webapp.security;

import com.github.annzem.banana.webapp.model.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenValAuthFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenRepository tokenRepository;
    //todo: only GET method
    public TokenValAuthFilter(AuthenticationManager authManager, String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authManager);
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String tokenVal = request.getParameter("token_val");
        if (tokenVal == null) {
            return null;
        }

        TokenValAuthorizationToken authRequest = new TokenValAuthorizationToken(tokenVal);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}