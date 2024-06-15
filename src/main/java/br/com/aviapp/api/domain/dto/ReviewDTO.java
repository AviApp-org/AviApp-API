package br.com.aviapp.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private CollectDTO collectId;
    private EmployeeDTO reviewedBy;
}
