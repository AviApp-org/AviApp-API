package br.com.aviapp.api.domain.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EggValueDTO {
    
    private EggDTO eggId;
    private BigDecimal value;
}
