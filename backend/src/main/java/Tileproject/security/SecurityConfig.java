package Tileproject.security;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.*;
import org.springframework.security.access.hierarchicalroles.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;


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
        .csrf(csrf -> csrf.disable())
        .cors(cors -> {})
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth

            // ✅ CORS preflight (THIS WAS MISSING)
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

            // AUTH
            .requestMatchers("/api/auth/**").permitAll()

            // PUBLIC READ
            .requestMatchers(HttpMethod.GET,
                "/api/products/**",
                "/api/categories/**",
                "/api/brands/**"
            ).permitAll()

            // ADMIN
            .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")

            .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN")

            .requestMatchers(HttpMethod.POST, "/api/brands/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/brands/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/brands/**").hasRole("ADMIN")

            // CART
            .requestMatchers(HttpMethod.GET, "/api/cart/**").hasAnyRole("CUSTOMER","ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/cart/**").hasAnyRole("CUSTOMER","ADMIN")
            //.requestMatchers(HttpMethod.GET, "/api/cart/total").hasAnyRole("CUSTOMER","ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/cart/total").authenticated()

            // ADDRESS
            .requestMatchers("/api/user/**").hasAnyRole("CUSTOMER","ADMIN")

            // PAYMENT
            //.requestMatchers("/api/payment/**").hasAnyRole("CUSTOMER","ADMIN")
            
            // PAYMENT
.requestMatchers(HttpMethod.POST, "/api/payment/create").hasAnyRole("CUSTOMER","ADMIN")
.requestMatchers(HttpMethod.POST, "/api/payment/verify").permitAll()

.requestMatchers("/api/orders/**").hasAnyRole("CUSTOMER", "ADMIN")
.requestMatchers("/api/admin/**").hasRole("ADMIN")



            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler =
                new DefaultMethodSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy());
        return handler;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_CUSTOMER");
        return hierarchy;
    }
}
