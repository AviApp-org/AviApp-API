package br.com.aviapp.api.application.usecases.collect;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.gateways.CollectRepository;
import br.com.aviapp.api.application.mappers.CollectMapperBO;

public class ListAllCollectsUseCase {
    private final CollectRepository collectRepository;
    private final CollectMapperBO collectMapper;

    public ListAllCollectsUseCase(CollectRepository collectRepository, CollectMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public List<CollectDTO> invoke() {
        return collectRepository.getAllCollects().stream()
                .map(collectMapper::toBO)
                .map(collectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
