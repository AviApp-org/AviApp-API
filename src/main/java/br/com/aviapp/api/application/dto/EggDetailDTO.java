package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.enums.EnumEggType;

public record EggDetailDTO(EnumEggType type, Integer quantity) {
}
