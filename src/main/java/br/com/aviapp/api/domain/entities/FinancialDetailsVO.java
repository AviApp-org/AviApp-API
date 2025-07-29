package br.com.aviapp.api.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class FinancialDetailsVO {
    private String aviary;
    private BigDecimal hatchableTotal;
    private BigDecimal marketTotal;
    private BigDecimal total;

}
