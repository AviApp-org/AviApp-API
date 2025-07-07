package br.com.aviapp.api.application.usecases.eggValue;

import br.com.aviapp.api.application.gateways.IEggValue;

public class DeleteEggValueUseCase {

    private final IEggValue repository;

    public DeleteEggValueUseCase(IEggValue repository) {
        this.repository = repository;
    }

    public void invoke(Long eggValueId) {
        repository.deleteEggValue(eggValueId);
    }
}
