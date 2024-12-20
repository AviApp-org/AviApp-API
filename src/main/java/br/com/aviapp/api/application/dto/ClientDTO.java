package br.com.aviapp.api.application.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import jakarta.validation.constraints.Email;

public record ClientDTO(Long id,
    String name,

    @Email String email,

    @CNPJ String cnpj,

    String phone,
    EnumStatusCliente status) {
  
      public ClientDTO {
    if (status == null) {
      status = EnumStatusCliente.ACTIVE;
    }
  }
}
