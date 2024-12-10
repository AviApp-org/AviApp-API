package br.com.aviapp.api.application.dto;

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
    
    private Long aviaryId;
    private LocalDateTime timestamp;
    private Integer stage;
}
