package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ListEggCollectsByDateAndAviaryUseCase {
    private final CollectEggRepository collectEggRepository;
    private final CollectEggMapperBO collectEggMapper;

    public ListEggCollectsByDateAndAviaryUseCase(CollectEggRepository collectEggRepository, CollectEggMapperBO collectEggMapper) {
        this.collectEggRepository = collectEggRepository;
        this.collectEggMapper = collectEggMapper;
    }

    public List<CollectEggDataDTO> execute(Long aviaryId, LocalDateTime date) {
        return collectEggRepository.getEggCollectByAviaryAndDate(aviaryId, date).stream()
                .map(collectEggMapper::toBO)
                .map(collectEggMapper::toDTO)
                .collect(Collectors.toList());
    }
}
