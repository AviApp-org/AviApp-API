package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.FarmRepository;
import br.com.aviapp.api.infra.mappers.FarmMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.FarmRepositoryJPA;

@Repository
public class FarmRepositoryImpl implements FarmRepository {

    private final FarmRepositoryJPA farmRepositoryJPA;
    private final FarmMapperEntity farmMapper;

    public FarmRepositoryImpl(FarmRepositoryJPA farmRepositoryJPA, FarmMapperEntity farmMapper) {
        this.farmRepositoryJPA = farmRepositoryJPA;
        this.farmMapper = farmMapper;
    }

    @Override
    public FarmDTO createFarm(FarmDTO farm) {
        MySqlFarmEntity farmEntity = farmMapper.toEntity(farm);
        MySqlFarmEntity savedFarmEntity = farmRepositoryJPA.save(farmEntity);
        return farmMapper.toDTO(savedFarmEntity);
    }

    @Override
    public List<FarmDTO> listAllFarms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAllFarms'");
    }

    @Override
    public Optional<FarmDTO> findFarm(Long farmID) {
        return farmRepositoryJPA.findById(farmID)
                .map(farmMapper::toDTO);
    }

    @Override
    public void deleteFarm(Long farmID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFarm'");
    }

    @Override
    public Optional<FarmDTO> updateFarm(Long farmID, FarmDTO farm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFarm'");
    }

    @Override
    public void addBatchToFarm(Long farmId, Long batchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBatchToFarm'");
    }

    @Override
    public void removeBatchFromFarm(Long farmId, Long batchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeBatchFromFarm'");
    }

    @Override
    public void addEmployeeToFarm(Long farmId, Long employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEmployeeToFarm'");
    }

    @Override
    public void removeEmployeeFromFarm(Long farmId, Long employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeEmployeeFromFarm'");
    }

    @Override
    public List<FarmDTO> findFarmsByClientId(Long clientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findFarmsByClientId'");
    }

    @Override
    public void updateFarmAddress(Long farmId, AddressDTO address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFarmAddress'");
    }

}
