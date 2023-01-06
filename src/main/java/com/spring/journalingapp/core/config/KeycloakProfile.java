package com.spring.journalingapp.core.config;

import com.spring.journalingapp.core.BaseException;
import com.spring.journalingapp.core.exception.MessageConstant;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;

@Component
public class KeycloakProfile {
    public String getUsername() {
        String username = (String) getAccessToken().getPreferredUsername();
        if (username == null || username.isEmpty()) {
            throw new BaseException(MessageConstant.USER_NOT_FOUND);
        }

        return username;
    }

    public String getEmail() {
        String email = (String) getAccessToken().getEmail();
        if (email == null || email.isEmpty()) {
            throw new BaseException(MessageConstant.USER_NOT_FOUND);
        }

        return email;
    }

    public String getUsernameNullable() {
        if(getAccessToken() != null) {
            return (String) getAccessToken().getPreferredUsername();
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public AccessToken getAccessToken() throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (authentication != null) {
            Principal principal = attributes.getRequest().getUserPrincipal();
            if (principal instanceof KeycloakPrincipal) {
                return KeycloakPrincipal.class.cast(principal).getKeycloakSecurityContext().getToken();
            }
        }
        return null;
    }

    public boolean hasCustomRole(Authentication authentication, String... type){
        String typeTransaction = type.length > 0 ? type[0] : "";
        if (authentication instanceof KeycloakAuthenticationToken) {
            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>)authentication.getPrincipal();
            AccessToken token = kp.getKeycloakSecurityContext().getToken();

            for (String strRole : token.getRealmAccess().getRoles()) {
                if (!"".equalsIgnoreCase(typeTransaction) && strRole.toLowerCase().contains(typeTransaction.toLowerCase()))
                    return true;
            }
        }
        return false;
    }
}
