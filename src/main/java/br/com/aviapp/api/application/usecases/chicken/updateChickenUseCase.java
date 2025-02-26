package br.com.aviapp.api.application.usecases.chicken;

import java.util.Optional;

import br.com.aviapp.api.application.dto.ChickenDTO;
import br.com.aviapp.api.application.gateways.ChickenRepository;
import br.com.aviapp.api.application.mappers.ChickenMapperBO;
import br.com.aviapp.api.domain.entities.ChickenBO;

public class updateChickenUseCase {
    private final ChickenRepository chickenRepository;
    private final ChickenMapperBO chickenMapperBO;

    public updateChickenUseCase(ChickenRepository chickenRepository, ChickenMapperBO chickenMapperBO) {
        this.chickenRepository = chickenRepository;
        this.chickenMapperBO = chickenMapperBO;
    }

    public Optional<ChickenDTO> invoke(ChickenDTO chickenDTO, Long id) {
        ChickenBO chickenBO = chickenMapperBO.toBO(chickenDTO);
        return chickenRepository.updateChicken(chickenMapperBO.toDTO(chickenBO), id);
    }
}
