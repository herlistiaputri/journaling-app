package com.spring.journalingapp.core.config;

import com.spring.journalingapp.core.BaseException;
import com.spring.journalingapp.core.exception.MessageConstant;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class KeycloakProfile {

    public String getUsername() {
        String username = (String) getAccessToken().getPreferredUsername();
        if (username == null || username.isEmpty()) {
            throw new BaseException(MessageConstant.USER_NOT_FOUND);
        }

        return username;
    }

    public String getUsernameNullable() {
        if(getAccessToken() != null) {
            return (String) getAccessToken().getPreferredUsername();
        } else {
            return null;
        }
    }

    public String getEmail() {
        if(getAccessToken() != null) {
            return (String) getAccessToken().getEmail();
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public AccessToken getAccessToken() throws AuthenticationException {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = null;
        if (principal != null && principal instanceof KeycloakPrincipal) {
            keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
        }

        AccessToken accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();
        return accessToken;
    }
}
