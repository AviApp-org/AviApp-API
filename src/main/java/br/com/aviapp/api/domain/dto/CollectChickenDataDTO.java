package br.com.aviapp.api.domain.dto;

import br.com.aviapp.api.infra.mysql.enums.ChickenDeathCause;
import br.com.aviapp.api.infra.mysql.enums.ChickenGender;
import br.com.aviapp.api.infra.mysql.enums.ChickenStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CollectChickenDataDTO {
    
    private CollectDTO collect;
    private ChickenGender gender;
    private Integer quantity;
    private ChickenDeathCause deathCause;
    private ChickenStatus status;
    private String observation;
}
