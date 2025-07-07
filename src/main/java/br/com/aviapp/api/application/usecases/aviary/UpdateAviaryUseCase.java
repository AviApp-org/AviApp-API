package br.com.aviapp.api.application.usecases.aviary;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.IAviary;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;

import java.util.Optional;

public class UpdateAviaryUseCase {

    private final IAviary repository;
    private final AviaryMapperBO mapperBO;

    public UpdateAviaryUseCase(IAviary repository, AviaryMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public Optional<AviaryDTO> invoke(Long aviaryId, AviaryDTO aviaryDTO) {

        return repository.updateAviary(aviaryId, aviaryDTO);
    }
}
