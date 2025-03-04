package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.CollectChickenRepository;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;

import java.util.List;
import java.util.stream.Collectors;

public class ListChickenCollectsByAviaryUseCase {
    private final CollectChickenRepository collectRepository;
    private final CollectChickenMapperBO collectMapper;

    public ListChickenCollectsByAviaryUseCase(CollectChickenRepository collectRepository, CollectChickenMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public List<CollectChickenDTO> invoke(Long aviaryId) {
        return collectRepository.listChickenCollectByAviary(aviaryId).stream()
                .map(collectMapper::toBO)
                .map(collectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
