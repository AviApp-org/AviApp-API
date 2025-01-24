package br.com.aviapp.api.application.usecases.aviary;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.AviaryRepository;

public class ListAviariesByBatchUseCase {
    private final AviaryRepository aviaryRepository;

    public ListAviariesByBatchUseCase(AviaryRepository aviaryRepository) {
        this.aviaryRepository = aviaryRepository;
    }

    public Optional<List<AviaryDTO>> invoke(Long batchId) {
        return aviaryRepository.listAllAviarysByBatchId(batchId);
    }
}
