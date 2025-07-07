package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.ICollectChicken;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ListChickenCollectsByDateAndAviaryUseCase {

    private final ICollectChicken collectChickenRepository;
    private final CollectChickenMapperBO collectChickenMapper;

    public ListChickenCollectsByDateAndAviaryUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        this.collectChickenRepository = collectChickenRepository;
        this.collectChickenMapper = collectChickenMapper;
    }

    public List<CollectChickenDTO> invoke(Long aviaryId, LocalDate date) {
        return collectChickenRepository.getChickenCollectByAviaryAndDate(aviaryId, date).stream()
                .map(collectChickenMapper::toBO)
                .map(collectChickenMapper::toDTO)
                .collect(Collectors.toList());

    }
}
