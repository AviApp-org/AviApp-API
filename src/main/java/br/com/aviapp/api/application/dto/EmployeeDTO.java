package br.com.aviapp.api.application.dto;

import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumEmployeeRole;

public record EmployeeDTO(Long id, String name,String cpf, String phone, EnumEmployeeRole role, Date createdAt, Long farmId) {

}
