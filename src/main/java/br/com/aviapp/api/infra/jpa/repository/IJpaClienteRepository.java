package br.com.aviapp.api.infra.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.jakarta.models.JakartaCliente;

@Repository
public interface IJpaClienteRepository extends JpaRepository<JakartaCliente, Long> {

}
