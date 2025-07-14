package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlUserCredentials;

import java.util.Optional;

@Repository
public interface UserCredentialsRepositoryJPA  extends JpaRepository<MySqlUserCredentials, Long> {
   UserDetails findByLogin(String login);
}
