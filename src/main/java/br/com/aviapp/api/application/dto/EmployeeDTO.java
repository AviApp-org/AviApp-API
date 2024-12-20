package br.com.aviapp.api.application.dto;

import java.util.Date;

import br.com.aviapp.api.domain.enums.EnumEmployeeRole;

public record EmployeeDTO(Long id, String name, EnumEmployeeRole role, Date createdAt, Long farmId) {

}
