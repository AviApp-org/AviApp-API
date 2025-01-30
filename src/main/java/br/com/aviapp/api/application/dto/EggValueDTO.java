package br.com.aviapp.api.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.aviapp.api.domain.enums.EnumEggType;

public record EggValueDTO(Long eggId, EnumEggType egg, LocalDateTime timeStamp, BigDecimal value) {

}
