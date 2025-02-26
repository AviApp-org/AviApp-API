package br.com.aviapp.api.application.dto;

import java.time.LocalDateTime;

public record CollectDTO(Long id, Long aviaryId, Long employeeId, LocalDateTime timestamp) {

}
