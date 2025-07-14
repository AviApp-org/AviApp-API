package br.com.aviapp.api.application.dto;


public record AddressDTO (Long id, String street, String number, String cep, String neighborhood, String city, String state, Long farmId) {

}
