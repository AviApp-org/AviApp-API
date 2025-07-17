package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.gateways.IFarm;
import br.com.aviapp.api.application.mappers.FarmMapperBO;
import br.com.aviapp.api.domain.entities.FarmBO;
import br.com.aviapp.api.domain.errors.BusinessRuleException;

public class DeleteFarmByIdUseCase {

    private final IFarm repository;

    private final FarmMapperBO mapper;

    public DeleteFarmByIdUseCase(IFarm repository, FarmMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void invoke(Long id) throws BusinessRuleException {

        FarmBO farmBO = mapper.toBO(repository.findFarm(id).orElseThrow( () -> new RuntimeException("Farm not found")));

        farmBO.validateDeletion();

        repository.deleteFarm(id);
    }
}
