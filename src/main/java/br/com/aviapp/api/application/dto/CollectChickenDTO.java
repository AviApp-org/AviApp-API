package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.enums.EnumChickenDeathCause;


public record CollectChickenDTO(Long id, Long collectId,
        Integer deadRoosters,
        Integer deadChickens,
        EnumChickenDeathCause deathCause,
        String observation) {

}
