package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.CollectChickenRepository;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ListChickenCollectsByDateAndAviaryUseCase {

    private final CollectChickenRepository collectChickenRepository;
    private final CollectChickenMapperBO collectChickenMapper;

    public ListChickenCollectsByDateAndAviaryUseCase(CollectChickenRepository collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        this.collectChickenRepository = collectChickenRepository;
        this.collectChickenMapper = collectChickenMapper;
    }

    public List<CollectChickenDTO> invoke(Long aviaryId, LocalDateTime date) {
        return collectChickenRepository.getChickenCollectByAviaryAndDate(aviaryId, date).stream()
                .map(collectChickenMapper::toBO)
                .map(collectChickenMapper::toDTO)
                .collect(Collectors.toList());

    }
}
