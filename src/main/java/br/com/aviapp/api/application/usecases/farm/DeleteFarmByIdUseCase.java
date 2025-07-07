package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.gateways.IFarm;

public class DeleteFarmByIdUseCase {

    private final IFarm repository;

    public DeleteFarmByIdUseCase(IFarm repository) {
        this.repository = repository;
    }

    public void invoke(Long id) {
        repository.deleteFarm(id);
    }
}
