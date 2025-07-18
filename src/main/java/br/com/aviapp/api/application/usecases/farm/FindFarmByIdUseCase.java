package br.com.aviapp.api.application.usecases.farm;

import java.util.Optional;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.IFarm;
import br.com.aviapp.api.application.mappers.FarmMapperBO;

public class FindFarmByIdUseCase {
    private final IFarm repository;
    private final FarmMapperBO mapper;

    public FindFarmByIdUseCase(IFarm repository, FarmMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<FarmDTO> invoke(Long farmId) {
        return repository.findFarm(farmId)
                .map(mapper::toBO)
                .map(mapper::toDTO);
    }
}
