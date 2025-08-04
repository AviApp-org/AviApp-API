package br.com.aviapp.api.infra.services.security;

import br.com.aviapp.api.infra.mysql.repository.UserCredentialsRepositoryJPA;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserCredentialsRepositoryJPA repositoryJPA;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        if (path.startsWith("/ws-collect")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);

        try {
            if (token != null) {
                var login = tokenService.validateToken(token); // Pode lançar exceção
                UserDetails user = repositoryJPA.findByLogin(login);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);

        } catch (RuntimeException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("""
                            {
                              "timestamp": "%s",
                              "status": 401,
                              "error": "Unauthorized",
                              "message": "%s",
                              "path": "%s"
                            }
                            """.formatted(
                            java.time.LocalDateTime.now(),
                            ex.getMessage(),
                            request.getRequestURI()
                    )
            );
        }
    }


    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
