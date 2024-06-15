package br.com.aviapp.api.domain.dto;

import br.com.aviapp.api.infra.mysql.enums.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTypeDTO {
    
    private EmployeeRole description;
}
