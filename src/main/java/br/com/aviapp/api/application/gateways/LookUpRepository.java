package br.com.aviapp.api.application.gateways;

import java.util.Optional;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.dto.FarmDTO;

public interface LookUpRepository {
    Optional<ClientDTO> findClientDTOById(Long id);

    Optional<AddressDTO> findAddressDTOById(Long id);

    Optional<EmployeeDTO> findEmployeeDTOById(Long id);

    Optional<FarmDTO> findFarmDTOById(Long id);

    Optional<BatchDTO> findBatchDTOById(Long id);
}
