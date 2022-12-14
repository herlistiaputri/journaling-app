package com.spring.journalingapp.core.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String fullName = "user";

        return Optional.ofNullable(fullName);
    }
}
