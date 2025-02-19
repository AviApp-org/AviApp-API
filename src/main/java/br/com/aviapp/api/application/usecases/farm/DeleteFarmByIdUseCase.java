package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.gateways.FarmRepository;

public class DeleteFarmByIdUseCase {

    private final FarmRepository repository;

    public DeleteFarmByIdUseCase(FarmRepository repository) {
        this.repository = repository;
    }

    public void invoke(Long id) {
        repository.deleteFarm(id);
    }
}
