package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.ICollectEgg;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ListEggCollectsByDateAndAviaryUseCase {
    private final ICollectEgg collectEggRepository;
    private final CollectEggMapperBO collectEggMapper;

    public ListEggCollectsByDateAndAviaryUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapper) {
        this.collectEggRepository = collectEggRepository;
        this.collectEggMapper = collectEggMapper;
    }

    public List<CollectEggDataDTO> invoke(Long aviaryId, LocalDate date) {
        return collectEggRepository.getEggCollectByAviaryAndDate(aviaryId, date).stream()
                .map(collectEggMapper::toBO)
                .map(collectEggMapper::toDTO)
                .collect(Collectors.toList());
    }
}
