package br.com.aviapp.api.application.dto;

public record CepResponseDTO(String street, String cep, String neighborhood, String city, String state) {
}
