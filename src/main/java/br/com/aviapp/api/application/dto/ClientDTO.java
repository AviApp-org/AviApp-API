package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;

public record ClientDTO(Long id, String name, @Email String email, @CNPJ String cnpj, String phone, EnumStatusCliente status) {
}
