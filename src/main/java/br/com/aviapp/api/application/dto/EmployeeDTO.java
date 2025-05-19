package br.com.aviapp.api.application.dto;

import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumEmployeeRole;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeeDTO(Long id, String name,
                          @CPF String cpf, Date birthDate, String phone, EnumEmployeeRole role, Date createdAt, Long farmId) {
}
