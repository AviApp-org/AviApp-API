package br.com.aviapp.api.application.usecases.aviary;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.IAviary;

public class ListAviariesByBatchUseCase {
    private final IAviary aviaryRepository;

    public ListAviariesByBatchUseCase(IAviary aviaryRepository) {
        this.aviaryRepository = aviaryRepository;
    }

    public Optional<List<AviaryDTO>> invoke(Long batchId) {
        return aviaryRepository.listAllAviarysByBatchId(batchId);
    }
}
