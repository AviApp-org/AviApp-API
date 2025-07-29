package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.domain.entities.FinancialDetailsVO;

import java.math.BigDecimal;
import java.util.List;

public record FinancialDetailsDTO(List<FinancialDetailsVO> aviaryDetails, BigDecimal hatchableTotal,
                                  BigDecimal marketTotal, BigDecimal total) {
}
