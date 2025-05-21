package br.com.aviapp.api.application.usecases.aviary;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.AviaryRepository;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.domain.entities.AviaryBO;

import java.util.Optional;

public class UpdateAviaryUseCase {

    private final AviaryRepository repository;
    private final AviaryMapperBO mapperBO;

    public UpdateAviaryUseCase(AviaryRepository repository, AviaryMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public Optional<AviaryDTO> invoke(Long aviaryId, AviaryDTO aviaryDTO) {

        return repository.updateAviary(aviaryId, aviaryDTO);
    }
}
