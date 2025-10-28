package com.muts.treinoSecurity.infra.security.config;

import com.muts.treinoSecurity.infra.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecuirtyConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;
}
