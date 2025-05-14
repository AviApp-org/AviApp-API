package br.com.aviapp.api.application.dto;

import java.util.List;

public record FarmDTO (Long id, String name, Long clientId, Long addressId, List<Long> employeesId) {
}
