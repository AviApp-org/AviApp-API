package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.enums.EnumEggType;

public record EggDetailPercentageDTO(EnumEggType type, double percentage) {
}
