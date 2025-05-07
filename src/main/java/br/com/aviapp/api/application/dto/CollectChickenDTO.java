package br.com.aviapp.api.application.dto;


public record CollectChickenDTO(Long id, Long aviaryId,
        Integer deadRoosters,
        Integer deadChickens,
        String observation) {

}
