package com.fiap.reserva_equipamentos.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    // Swagger livre
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                    // Regras específicas para Professores
                    .requestMatchers(HttpMethod.GET, "/api/professores/**").hasAnyRole("PROF", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/professores/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/professores/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/professores/**").hasRole("ADMIN")

                    // Demais recursos já existentes
                    .requestMatchers("/api/equipamentos/**", "/api/reservas/**").hasAnyRole("PROF", "ADMIN")

                    // Qualquer outra rota exige autenticação
                    .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    UserDetailsService users(PasswordEncoder encoder) {
        UserDetails prof = User.withUsername("professor").password(encoder.encode("123456")).roles("PROF").build();
        UserDetails admin = User.withUsername("admin").password(encoder.encode("admin123")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(prof, admin);
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}