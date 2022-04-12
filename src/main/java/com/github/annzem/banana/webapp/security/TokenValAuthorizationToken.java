package com.github.annzem.banana.webapp.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenValAuthorizationToken extends AbstractAuthenticationToken {

    private String tokenVal;

    public TokenValAuthorizationToken(String tokenVal) {
        super(null);
        this.tokenVal = tokenVal;
        setAuthenticated(false);
    }

    public TokenValAuthorizationToken(UserDetails userDetails, String tokenVal) {
        super(null);
        this.tokenVal = tokenVal;
        this.setDetails(userDetails);
        setAuthenticated(true);
    }

    public Object getCredentials() {
        return tokenVal;
    }

    public Object getPrincipal() {
        return null;
    }

    public String getTokenVal() {
        return tokenVal;
    }
}
