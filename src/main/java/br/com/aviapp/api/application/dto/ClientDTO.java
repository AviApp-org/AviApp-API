package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;

public record ClientDTO(Long id, String name, String email, String cnpj, String phone, EnumStatusCliente status) {
}
