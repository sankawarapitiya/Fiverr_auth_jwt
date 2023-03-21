package com.nbkarthi.auth_jwt.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;

public class SignatureAuthenticationToken extends AbstractAuthenticationToken implements Serializable {

    private final Object principal;
    private final String publicKey;

    public SignatureAuthenticationToken(String publicKey, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.publicKey = publicKey;
        this.principal = principal;
        this.setAuthenticated(true);
    }
    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
