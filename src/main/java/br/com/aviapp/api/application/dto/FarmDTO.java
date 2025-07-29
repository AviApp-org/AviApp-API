package br.com.aviapp.api.application.dto;

import java.util.List;

public record FarmDTO (Long id, String name, Long clientId, List<Long> employeesId, String street, String number, String cep, String neighborhood, String city, String state) {
}
