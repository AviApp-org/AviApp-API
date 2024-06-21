package br.com.aviapp.api.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FarmDTO {
    private Long id;
    private String name;
    private AddressDTO address;
    private Long addressId;
    private Long clientId;
    private List<EmployeeDTO> employeeId;
}
