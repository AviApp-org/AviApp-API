package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;

import java.util.List;
import java.util.stream.Collectors;

public class ListAllEggCollectsUseCase {
    private final CollectEggRepository collectRepository;
    private final CollectEggMapperBO collectMapper;

    public ListAllEggCollectsUseCase(CollectEggRepository collectRepository, CollectEggMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public List<CollectEggDataDTO> invoke() {
        return collectRepository.getAllEggCollects().stream()
                .map(collectMapper::toBO)
                .map(collectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
