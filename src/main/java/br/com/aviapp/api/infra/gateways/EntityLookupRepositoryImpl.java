package br.com.aviapp.api.infra.gateways;

import java.util.Optional;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.AddressRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.AviaryRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.BatchRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.ClientRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.EmployeeRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import br.com.aviapp.api.infra.mysql.repository.FarmRepositoryJPA;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class EntityLookupRepositoryImpl implements EntityLookupRepository {
    private final ClientRepositoryJPA clientRepository;
    private final AddressRepositoryJPA addressRepository;
    private final EmployeeRepositoryJPA employeeRepository;
    private final FarmRepositoryJPA farmRepository;
    private final BatchRepositoryJPA batchRepository;
    private final AviaryRepositoryJPA aviaryRepository;

    // EntityLookupRepository implementation
    @Override
    public Optional<MySqlClientEntity> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Optional<MySqlAddressEntity> findAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Optional<MySqlEmployeeEntity> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<MySqlFarmEntity> findFarmById(Long id) {
        return farmRepository.findById(id);
    }

    @Override
    public Optional<MySqlBatchEntity> findBatchById(Long id) {
        return batchRepository.findById(id);
    }

    @Override
    public Optional<MySqlAviaryEntity> findAviaryById(Long id) {
        return aviaryRepository.findById(id);
    }

    // BusinessLookupService implementation

}
