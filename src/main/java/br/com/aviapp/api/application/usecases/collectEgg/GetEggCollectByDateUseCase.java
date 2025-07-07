package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.ICollectEgg;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GetEggCollectByDateUseCase {
    private final ICollectEgg collectRepository;
    private final CollectEggMapperBO collectMapper;

    public GetEggCollectByDateUseCase(ICollectEgg collectRepository, CollectEggMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public List<CollectEggDataDTO> invoke(LocalDate date) {
        return collectRepository.getEggCollectByDate(date).stream()
                .map(collectMapper::toBO)
                .map(collectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
