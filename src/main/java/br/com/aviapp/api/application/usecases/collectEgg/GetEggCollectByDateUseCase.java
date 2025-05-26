package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GetEggCollectByDateUseCase {
    private final CollectEggRepository collectRepository;
    private final CollectEggMapperBO collectMapper;

    public GetEggCollectByDateUseCase(CollectEggRepository collectRepository, CollectEggMapperBO collectMapper) {
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
