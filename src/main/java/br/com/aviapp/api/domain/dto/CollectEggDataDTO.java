package br.com.aviapp.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CollectEggDataDTO {
    
    private CollectDTO collect;
    private EggDTO eggId;
    private Integer quantity;
    
}
