package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.infra.mysql.enums.ChickenDeathCause;
import br.com.aviapp.api.infra.mysql.enums.ChickenGender;
import br.com.aviapp.api.infra.mysql.enums.ChickenStatus;


public record CollectChickenDataDTO(CollectDTO collect,
        ChickenGender gender,
        Integer quantity,
        ChickenDeathCause deathCause,
        ChickenStatus status,
        String observation) {

}
