package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.IFarm;

import java.util.Optional;

public class GetFarmByClientIdUseCase {

    private final IFarm farmRepository;


    public GetFarmByClientIdUseCase(IFarm farmRepository) {
        this.farmRepository = farmRepository;
    }

    public Optional<FarmDTO> invoke (Long clientId) {

        return farmRepository.findFarmByClient(clientId);
    }
}
