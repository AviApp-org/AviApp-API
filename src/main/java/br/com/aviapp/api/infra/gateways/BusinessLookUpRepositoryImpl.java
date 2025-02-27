package br.com.aviapp.api.infra.gateways;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.infra.mappers.AddressMapperEntity;
import br.com.aviapp.api.infra.mappers.AviaryMapperEntity;
import br.com.aviapp.api.infra.mappers.BatchMapperEntity;
import br.com.aviapp.api.infra.mappers.ClientMapperEntity;
import br.com.aviapp.api.infra.mappers.CollectMapperEntity;
import br.com.aviapp.api.infra.mappers.EmployeeMapperEntity;
import br.com.aviapp.api.infra.mappers.FarmMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.AddressRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.AviaryRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.BatchRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.ClientRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.CollectRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.EmployeeRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.FarmRepositoryJPA;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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
    private final BatchMapperEntity batchMapper;
    private final BatchRepositoryJPA batchRepository;
    private final  AviaryMapperEntity aviaryMapper;
    private final AviaryRepositoryJPA aviaryRepository;
    private final CollectMapperEntity collectMapper;
    private final CollectRepositoryJPA collectRepository;


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
        return farmRepository.findById(id)
                .map(farmMapper::toDTO);
    }

    @Override
    public Optional<BatchDTO> findBatchDTOById(Long id) {
        return batchRepository.findById(id)
                .map(batchMapper::toDTO);
    }

    @Override
    public Optional<AviaryDTO> findAviaryDTOById(Long id) {
        return aviaryRepository.findById(id)
        .map(aviaryMapper::toDTO);
    }

    @Override
    public Optional<CollectDTO> findCollectById(Long id) {
        return collectRepository.findById(id)
        .map(collectMapper::toDTO);
    }

}
