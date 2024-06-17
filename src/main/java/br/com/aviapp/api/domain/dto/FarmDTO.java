package br.com.aviapp.api.domain.dto;

import java.util.List;

import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
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
    private AddressDTO addressId;
    private ClientDTO clientId;
    private List<EmployeeDTO> employeeId;

    public static FarmDTO fromEntity(MySqlFarmEntity entity) {
        FarmDTO dto = new FarmDTO();
        // Mapear os campos da entidade para o DTO
        return dto;
    }
}
