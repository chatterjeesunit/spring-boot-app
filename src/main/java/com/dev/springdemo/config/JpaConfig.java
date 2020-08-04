package com.dev.springdemo.config;

import com.dev.springdemo.audit.AuditAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "customAuditProvider")
public class JpaConfig {

    @Bean
    public AuditorAware<String> customAuditProvider() {
        return new AuditAwareImpl();
    }
}
