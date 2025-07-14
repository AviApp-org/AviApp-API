package br.com.aviapp.api.infra.services.security;

import br.com.aviapp.api.infra.mysql.models.MySqlUserCredentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public static MySqlUserCredentials getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MySqlUserCredentials) {
            return (MySqlUserCredentials) authentication.getPrincipal();
        }
        return null;
    }

    public static Long getCurrentClientId() {
        MySqlUserCredentials user = getCurrentUser();
        return user != null ? user.getClient().getId() : null;
    }

    public static String getCurrentClientName() {
        MySqlUserCredentials user = getCurrentUser();
        return user != null ? user.getClient().getName() : null;
    }
}
