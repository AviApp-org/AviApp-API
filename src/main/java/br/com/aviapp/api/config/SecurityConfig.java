package br.com.aviapp.api.config;

import br.com.aviapp.api.infra.services.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("api/auth/refresh").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/addresses/cep/**").authenticated()

                        // Report endpoints
                        .requestMatchers(HttpMethod.GET, "/api/daily-report/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/financial-details/**").hasRole("MANAGER")

                        // Batch management
                        .requestMatchers(HttpMethod.GET, "/api/batches").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/batches/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/batches").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/batches/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/batches/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/batches/**").hasRole("MANAGER")

                        // Aviary management
                        .requestMatchers(HttpMethod.GET, "/api/aviaries").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/aviaries/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/aviaries").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/aviaries/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/aviaries/**").hasRole("MANAGER")

                        // Egg collection
                        .requestMatchers(HttpMethod.GET, "/api/collect-egg").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/collect-egg/date/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/collect-egg/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/collect-egg").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/collect-egg/**").hasRole("ADMIN")

                        // Chicken collection
                        .requestMatchers(HttpMethod.GET, "/api/collect-chicken").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/collect-chicken/date/**").hasRole("ADMIN")
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
                        .requestMatchers(HttpMethod.GET, "/api/farms").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/farms/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/farms").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/farms/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/farms/**").hasRole("ADMIN")

                        // Employee management (admin only)
                        .requestMatchers("/api/employees/**").hasRole("MANAGER")


                        .requestMatchers("/api/clients/**").hasRole("ADMIN")


                        .anyRequest().authenticated()
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5174"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}
