package Tileproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Disable CSRF (API only)
            .csrf(csrf -> csrf.disable())

            // ❌ Disable sessions (JWT)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // ✅ AUTH RULES
            .authorizeHttpRequests(auth -> auth

                // 🔓 AUTH SERVICE (LOGIN / REGISTER)
                .requestMatchers("/api/auth/**").permitAll()

                // 🔓 TEMP: Allow PRODUCT CREATION (for testing)
                .requestMatchers(HttpMethod.POST, "/api/products/**").permitAll()

                // 🔓 PUBLIC READ APIs
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/products/**",
                    "/api/categories/**",
                    "/api/brands/**"
                ).permitAll()

                // 🔐 EVERYTHING ELSE NEEDS JWT
                .anyRequest().authenticated()
            );

        // ✅ JWT FILTER
        http.addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
