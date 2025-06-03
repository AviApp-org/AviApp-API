package br.com.aviapp.api.application.dto;

import java.math.BigDecimal;

public record FinancialDetailsDTO(BigDecimal hatchableTotal, BigDecimal marketTotal, BigDecimal total) {
}
