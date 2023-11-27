package br.com.aviapp.api.infra.jakarta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.domain.entities.ClienteBO;

@Repository
public interface JakartaClienteRepository extends JpaRepository<ClienteBO, Long> {
  
}
