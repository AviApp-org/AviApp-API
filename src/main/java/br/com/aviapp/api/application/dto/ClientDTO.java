package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record ClientDTO(Long id,
                        @NotBlank String name,
                        @Email String email,
                        @CNPJ String cnpj,
                        @NotBlank String phone,
                        @NotBlank EnumStatusCliente status) {
}
