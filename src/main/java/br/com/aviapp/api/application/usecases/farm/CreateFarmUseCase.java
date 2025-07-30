package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.IFarm;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.FarmMapperBO;
import br.com.aviapp.api.domain.entities.FarmBO;
import br.com.aviapp.api.domain.errors.BusinessRuleException;

public class CreateFarmUseCase {
    
    private final IFarm repository;
    private final GetFarmByClientIdUseCase getFarmByClientIdUseCase;
    private final FarmMapperBO mapper;

    public CreateFarmUseCase(IFarm repository, GetFarmByClientIdUseCase getFarmByClientIdUseCase, FarmMapperBO mapper) {
        this.repository = repository;
        this.getFarmByClientIdUseCase = getFarmByClientIdUseCase;

        this.mapper = mapper;
    }

    public FarmDTO invoke(FarmDTO farmDTO) throws BusinessRuleException {
        FarmBO farmBO = mapper.toBO(farmDTO);

        if (getFarmByClientIdUseCase.invoke(farmDTO.clientId()).isPresent()) {
            throw new BusinessRuleException("Já existe uma granja cadastrada para esse cliente");
        }

        farmBO.validateForCreation();

        return repository.createFarm(mapper.toDTO(farmBO));
    }
    
}
