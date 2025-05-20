package br.com.aviapp.api.infra.gateways;

import br.com.aviapp.api.infra.mysql.repository.UserCredentialsRepositoryJPA;
import org.springframework.stereotype.Repository;

@Repository
public class UserCredentialsRepositoryImpl  {

    private final UserCredentialsRepositoryJPA repositoryJPA;

    public UserCredentialsRepositoryImpl(UserCredentialsRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

}
