package br.com.aviapp.api.application.dto;

import java.time.LocalDateTime;

public record WaterDTO(Long id, Long aviaryId, float volume, LocalDateTime collectionDate) {
}
