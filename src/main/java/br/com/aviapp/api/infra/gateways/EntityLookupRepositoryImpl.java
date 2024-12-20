package br.com.aviapp.api.infra.gateways;

import java.util.Optional;
import org.springframework.stereotype.Repository;


import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.AddressRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.ClientRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.EmployeeRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import br.com.aviapp.api.infra.mysql.repository.FarmRepositoryJPA;

@Repository
public class EntityLookupRepositoryImpl implements EntityLookupRepository {
    private final ClientRepositoryJPA clientRepository;
    private final AddressRepositoryJPA addressRepository;
    private final EmployeeRepositoryJPA employeeRepository;
    private final FarmRepositoryJPA farmRepository;

    public EntityLookupRepositoryImpl(
            ClientRepositoryJPA clientRepository,
            AddressRepositoryJPA addressRepository,
            EmployeeRepositoryJPA employeeRepository,
            FarmRepositoryJPA farmRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.farmRepository = farmRepository;

    }

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

    // BusinessLookupService implementation

}
