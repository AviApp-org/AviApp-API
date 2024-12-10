package br.com.aviapp.api.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    
    private String name;
    private Long roleId;
    private Long farmId;

}
