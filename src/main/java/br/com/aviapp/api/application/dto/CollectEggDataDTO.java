package br.com.aviapp.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CollectEggDataDTO {
    
    private Long collectId;
    private Long eggId;
    private Integer quantity;
    
}
