package br.com.aviapp.api.infra.services.security;

import br.com.aviapp.api.infra.mysql.repository.UserCredentialsRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserCredentialsRepositoryJPA repositoryJPA;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryJPA.findByLogin(username);
    }
}
