package br.com.aviapp.api.application.usecases.chicken;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.ChickenDTO;
import br.com.aviapp.api.application.gateways.ChickenRepository;
import br.com.aviapp.api.application.mappers.ChickenMapperBO;

public class listChickensUseCase {
    private final ChickenRepository chickenRepository;
    private final ChickenMapperBO chickenMapperBO;

    public listChickensUseCase(ChickenRepository chickenRepository, ChickenMapperBO chickenMapperBO) {
        this.chickenRepository = chickenRepository;
        this.chickenMapperBO = chickenMapperBO;
    }

    public List<ChickenDTO> invoke() {
        return chickenRepository.listAllChicken().stream()
                .map(chickenMapperBO::toBO)
                .map(chickenMapperBO::toDTO)
                .collect(Collectors.toList());
    }
}
