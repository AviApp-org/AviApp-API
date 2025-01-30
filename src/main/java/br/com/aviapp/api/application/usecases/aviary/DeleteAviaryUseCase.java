package br.com.aviapp.api.application.usecases.aviary;

import br.com.aviapp.api.application.gateways.AviaryRepository;

public class DeleteAviaryUseCase {

    private final AviaryRepository aviaryRepository;

    public DeleteAviaryUseCase(AviaryRepository aviaryRepository) {
        this.aviaryRepository = aviaryRepository;
    }

    public void invoke(Long aviaryId) {
        aviaryRepository.deleteAviary(aviaryId);
    }
}
