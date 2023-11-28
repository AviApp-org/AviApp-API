package br.com.aviapp.api.infra.jakarta.mappers;

import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.infra.jakarta.models.JakartaCliente;

public class JakartaClienteMapper {
  public static JakartaCliente toEntity(ClienteBO bo) {
    JakartaCliente entity = new JakartaCliente();

    entity.setCnpj(bo.getCnpj());
    entity.setEmail(bo.getEmail());
    entity.setNome(bo.getNome());
    entity.setStatus(bo.getStatus());
    entity.setTelefone(bo.getTelefone());

    return entity;
  }
}
