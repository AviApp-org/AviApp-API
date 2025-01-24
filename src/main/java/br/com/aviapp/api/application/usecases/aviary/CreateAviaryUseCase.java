package br.com.aviapp.api.application.usecases.aviary;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.AviaryRepository;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.domain.entities.AviaryBO;

public class CreateAviaryUseCase {

    private final AviaryRepository aviaryRepository;

    private final AviaryMapperBO aviaryMapperBO;

    public CreateAviaryUseCase(AviaryRepository aviaryRepository, AviaryMapperBO aviaryMapperBO) {
        this.aviaryRepository = aviaryRepository;
        this.aviaryMapperBO = aviaryMapperBO;
    }

    public AviaryDTO invoke(AviaryDTO aviaryDTO) {
        AviaryBO aviaryBO = aviaryMapperBO.toBO(aviaryDTO);
        AviaryDTO validatedAviary = aviaryMapperBO.toDTO(aviaryBO);
        return aviaryRepository.createAviary(validatedAviary);
    }
}
