package br.com.aviapp.api.application.dto;


import java.time.LocalDateTime;

public record CollectChickenDTO(Long id, Long aviaryId, Integer deadRoosters, Integer deadChickens, String observation, LocalDateTime collectionDate) {

}
