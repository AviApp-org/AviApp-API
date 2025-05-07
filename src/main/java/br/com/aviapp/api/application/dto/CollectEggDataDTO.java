package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.enums.EnumEggType;

public record CollectEggDataDTO(Long id,Long aviaryId, EnumEggType egg, Integer quantity) {

}
