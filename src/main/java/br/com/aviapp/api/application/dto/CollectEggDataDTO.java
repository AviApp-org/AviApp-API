package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.entities.EggDetailBO;
import br.com.aviapp.api.domain.enums.EnumEggType;

import java.time.LocalDateTime;
import java.util.List;

public record CollectEggDataDTO(Long id, Long aviaryId, List<EggDetailBO> eggDetailBOS, LocalDateTime collectionDate) {

}
