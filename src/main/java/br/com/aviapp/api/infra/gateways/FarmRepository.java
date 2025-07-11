package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.IFarm;
import br.com.aviapp.api.infra.mappers.FarmMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.FarmRepositoryJPA;

@Repository
public class FarmRepository implements IFarm {

    private final FarmRepositoryJPA farmRepositoryJPA;
    private final FarmMapperEntity farmMapper;

    public FarmRepository(FarmRepositoryJPA farmRepositoryJPA, FarmMapperEntity farmMapper) {
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
        return farmMapper.toDTOList(farmRepositoryJPA.findAll());
    }

    @Override
    public Optional<FarmDTO> findFarm(Long farmID) {
        return farmRepositoryJPA.findById(farmID)
                .map(farmMapper::toDTO);
    }

    @Override
    public Optional<FarmDTO> findFarmByClient(Long clientId) {
        MySqlFarmEntity mySqlFarmEntity = farmRepositoryJPA.findByClient(clientId).get();
        return Optional.ofNullable(farmMapper.toDTO(mySqlFarmEntity));
    }

    @Override
    public void deleteFarm(Long farmID) {
        farmRepositoryJPA.deleteById(farmID);
    }

    @Override
    public Optional<FarmDTO> updateFarm(Long farmID, FarmDTO farm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFarm'");
    }

}
