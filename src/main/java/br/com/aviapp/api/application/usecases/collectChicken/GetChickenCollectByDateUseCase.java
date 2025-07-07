package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.ICollectChicken;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GetChickenCollectByDateUseCase {
    private final ICollectChicken collectRepository;
    private final CollectChickenMapperBO collectMapper;

    public GetChickenCollectByDateUseCase(ICollectChicken collectRepository, CollectChickenMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public List<CollectChickenDTO> invoke(LocalDate date) {
        return collectRepository.getChickenCollectByDate(date).stream()
                .map(collectMapper::toBO)
                .map(collectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
