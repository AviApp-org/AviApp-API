package br.com.aviapp.api.application.usecases.collect;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.gateways.CollectRepository;
import br.com.aviapp.api.application.mappers.CollectMapperBO;

public class ListCollectsByAviaryUseCase {
    private final CollectRepository collectRepository;
    private final CollectMapperBO collectMapper;

    public ListCollectsByAviaryUseCase(CollectRepository collectRepository, CollectMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public List<CollectDTO> invoke(Long aviaryId) {
        return collectRepository.listCollectByAviary(aviaryId).stream()
                .map(collectMapper::toBO)
                .map(collectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
