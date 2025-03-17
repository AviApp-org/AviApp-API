package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;

import java.util.List;
import java.util.stream.Collectors;

public class ListEggCollectsByAviaryUseCase {
    private final CollectEggRepository collectRepository;
    private final CollectEggMapperBO collectMapper;

    public ListEggCollectsByAviaryUseCase(CollectEggRepository collectRepository, CollectEggMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public List<CollectEggDataDTO> invoke(Long aviaryId) {
        return collectRepository.listEggCollectByAviary(aviaryId).stream()
                .map(collectMapper::toBO)
                .map(collectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
