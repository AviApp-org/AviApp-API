package br.com.aviapp.api.application.dto;

import br.com.aviapp.api.infra.mysql.enums.EggType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EggDTO {
    
    private EggType type;
}
