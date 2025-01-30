package br.com.aviapp.api.application.dto;

import java.time.LocalDateTime;

public record CollectDTO(Long aviaryId, LocalDateTime timestamp, Integer stage) {

}
