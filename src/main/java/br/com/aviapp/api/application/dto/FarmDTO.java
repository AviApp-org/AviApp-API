package br.com.aviapp.api.application.dto;

import java.util.List;

public record FarmDTO (Long id, String name, Long addressId, Long clientId, List<Long> employeesId) {
}
