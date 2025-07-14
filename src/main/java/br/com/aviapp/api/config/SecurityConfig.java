package br.com.aviapp.api.config;

import br.com.aviapp.api.infra.services.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/addresses/cep/**").permitAll()

                        // Report endpoints
                        .requestMatchers(HttpMethod.GET, "/api/daily-report/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/financial-details/**").authenticated()

                        // Batch management
                        .requestMatchers(HttpMethod.GET, "/api/batches/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/batches").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/batches/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/batches/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/batches/**").hasRole("ADMIN")

                        // Aviary management
                        .requestMatchers(HttpMethod.GET, "/api/aviaries/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/aviaries").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/aviaries/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/aviaries/**").hasRole("ADMIN")

                        // Egg collection
                        .requestMatchers(HttpMethod.GET, "/api/collect-egg/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/collect-egg").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/collect-egg/**").hasRole("ADMIN")

                        // Chicken collection
                        .requestMatchers(HttpMethod.GET, "/api/collect-chicken/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/collect-chicken").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/collect-chicken/**").hasRole("ADMIN")

                        // Water records
                        .requestMatchers(HttpMethod.GET, "/api/water/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/water").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/water/**").hasRole("ADMIN")

                        // Egg value
                        .requestMatchers(HttpMethod.GET, "/api/egg-value/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/egg-value").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/egg-value/**").hasRole("ADMIN")

                        // Anomalies
                        .requestMatchers(HttpMethod.GET, "/api/anomalies/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/anomalies").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/anomalies/**").hasRole("ADMIN")

                        // Farm management
                        .requestMatchers(HttpMethod.GET, "/api/farms").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/farms/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/farms").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/farms/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/farms/**").hasRole("ADMIN")

                        // Employee management (admin only)
                        .requestMatchers("/api/employees/**").hasRole("ADMIN")

                        // Client management
                        .requestMatchers(HttpMethod.GET, "/api/clients/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/clients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/clients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/clients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/clients/**").hasRole("ADMIN")

                        // Address management
                        .requestMatchers(HttpMethod.GET, "/api/addresses").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/addresses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/addresses/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/addresses/**").hasRole("ADMIN")

                        // Deny all other requests
                        .anyRequest().denyAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
