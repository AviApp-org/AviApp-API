package br.com.aviapp.api.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CollectDTO {
    
    private AviaryDTO aviaryId;
    private LocalDateTime timestamp;
    private Integer stage;
}
