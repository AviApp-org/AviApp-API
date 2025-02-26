package br.com.aviapp.api.application.usecases.eggValue;

import br.com.aviapp.api.application.gateways.EggValueRepository;

public class DeleteEggValueUseCase {

    private final EggValueRepository repository;

    public DeleteEggValueUseCase(EggValueRepository repository) {
        this.repository = repository;
    }

    public void invoke(Long eggValueId) {
        repository.deleteEggValue(eggValueId);
    }
}
