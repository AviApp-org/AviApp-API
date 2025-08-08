package br.com.aviapp.api.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.aviapp.api.domain.enums.EnumEggType;

public record EggValueDTO(Long id, EnumEggType egg, LocalDateTime timeStamp, float value, Long batchId) {

}
