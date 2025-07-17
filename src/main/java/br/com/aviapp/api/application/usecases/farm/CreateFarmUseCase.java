package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.IFarm;
import br.com.aviapp.api.application.mappers.FarmMapperBO;
import br.com.aviapp.api.domain.entities.FarmBO;
import br.com.aviapp.api.domain.errors.BusinessRuleException;

public class CreateFarmUseCase {
    
    private final IFarm repository;
    private final FarmMapperBO mapper;

    public CreateFarmUseCase(IFarm repository, FarmMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FarmDTO invoke(FarmDTO farmDTO) throws BusinessRuleException {
        FarmBO farmBO = mapper.toBO(farmDTO);

        farmBO.validateForCreation();

        return repository.createFarm(mapper.toDTO(farmBO));
    }
    
}
