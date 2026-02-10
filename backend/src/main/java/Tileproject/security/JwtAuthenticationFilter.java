
package Tileproject.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

@Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
) throws ServletException, IOException {

    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
        try {
            String token = header.substring(7);
            Claims claims = jwtUtil.validateToken(token);

            // 🔥 .NET JWT format
            String email = claims.get("email", String.class);
            String role = claims.get(
                "http://schemas.microsoft.com/ws/2008/06/identity/claims/role",
                String.class
            );

            if (email == null || role == null) {
                throw new RuntimeException("Invalid JWT");
            }

            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
                );

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        System.out.println("🔥 JWT filter hit");
System.out.println("AUTH HEADER = " + header);

String token = header.substring(7);
Claims claims = jwtUtil.validateToken(token);

System.out.println("CLAIMS = " + claims);

String email = claims.get("email", String.class);
String role = claims.get("http://schemas.microsoft.com/ws/2008/06/identity/claims/role", String.class);

System.out.println("JWT EMAIL = " + email);
System.out.println("JWT ROLE = " + role);

    }

    filterChain.doFilter(request, response);
}

}
