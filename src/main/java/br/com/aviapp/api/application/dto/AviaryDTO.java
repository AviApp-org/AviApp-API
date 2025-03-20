package br.com.aviapp.api.application.dto;

public record AviaryDTO(Long id, String name,
                        Integer initialAmountOfRoosters,
                        Integer initialAmountOfChickens, Long batchId) {
}