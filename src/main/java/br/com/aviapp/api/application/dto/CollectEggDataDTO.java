package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.entities.EggDetailBO;

import java.time.LocalDateTime;
import java.util.List;

public record CollectEggDataDTO(Long id, Long aviaryId, List<EggDetailDTO> eggDetails, LocalDateTime collectionDate,
                                int marketEggs,
                                int dumpEggs,
                                int hatchableEggs,
                                int totalEggs) {

}
