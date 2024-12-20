package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.FarmRepository;

public class CreateFarmUseCase {
    
    private final FarmRepository repository;

    public CreateFarmUseCase(FarmRepository repository) {
        this.repository = repository;
    }

    public FarmDTO invoke(FarmDTO farmDTO) {
        return repository.createFarm(farmDTO);
    }
    
}
