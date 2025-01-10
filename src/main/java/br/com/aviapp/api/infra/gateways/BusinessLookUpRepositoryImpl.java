package br.com.aviapp.api.infra.gateways;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.infra.mappers.AddressMapperEntity;
import br.com.aviapp.api.infra.mappers.ClientMapperEntity;
import br.com.aviapp.api.infra.mappers.EmployeeMapperEntity;
import br.com.aviapp.api.infra.mappers.FarmMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.AddressRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.ClientRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.EmployeeRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.FarmRepositoryJPA;

@Repository
public class BusinessLookUpRepositoryImpl implements LookUpRepository {
    private final ClientRepositoryJPA clientRepository;
    private final AddressRepositoryJPA addressRepository;
    private final EmployeeRepositoryJPA employeeRepository;
    private final FarmRepositoryJPA farmRepository;
    private final ClientMapperEntity clientMapper;
    private final AddressMapperEntity addressMapper;
    private final EmployeeMapperEntity employeeMapper;
    private final FarmMapperEntity farmMapper;

    public BusinessLookUpRepositoryImpl(ClientRepositoryJPA clientRepository, AddressRepositoryJPA addressRepository,
            EmployeeRepositoryJPA employeeRepository, FarmRepositoryJPA farmRepository, ClientMapperEntity clientMapper,
            AddressMapperEntity addressMapper, EmployeeMapperEntity employeeMapper, FarmMapperEntity farmMapper) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.farmRepository = farmRepository;
        this.clientMapper = clientMapper;
        this.addressMapper = addressMapper;
        this.employeeMapper = employeeMapper;
        this.farmMapper = farmMapper;
    }

    @Override
    public Optional<ClientDTO> findClientDTOById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDTO);
    }

    @Override
    public Optional<AddressDTO> findAddressDTOById(Long id) {
        return addressRepository.findById(id)
                .map(addressMapper::toDTO);
    }

    @Override
    public Optional<EmployeeDTO> findEmployeeDTOById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDTO);
    }

    @Override
    public Optional<FarmDTO> findFarmDTOById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return farmRepository.findById(id)
                .map(farmMapper::toDTO);
    }
    

}
