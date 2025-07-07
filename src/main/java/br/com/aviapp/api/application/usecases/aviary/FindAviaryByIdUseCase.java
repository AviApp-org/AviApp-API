package br.com.aviapp.api.application.usecases.aviary;

import java.util.Optional;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.IAviary;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;

public class FindAviaryByIdUseCase {
    private final IAviary aviaryRepository;
    private final AviaryMapperBO mapper;

    public FindAviaryByIdUseCase(IAviary aviaryRepository, AviaryMapperBO aviaryMapperBO) {
        this.aviaryRepository = aviaryRepository;
        this.mapper = aviaryMapperBO;
    }

    public Optional<AviaryDTO> invoke(Long aviaryId) {
        return aviaryRepository.findAviaryById(aviaryId)
                .map(mapper::toBO) // Convert to domain for any business rules
                .map(mapper::toDTO); // Convert back to DTO for response
    }
}
